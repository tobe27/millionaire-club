package com.millionaire.millionaireclientweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.millionaire.millionairebusinessservice.exception.TimerTaskException;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.ContractResponse;
import com.millionaire.millionaireclientweb.result.ResultBean;
import com.millionaire.millionaireclientweb.util.CookieUtil;
import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.Constants;
import com.millionaire.millionairepaymentmanager.fuyou.until.MD5;
import com.millionaire.millionairepaymentmanager.manager.PayBackManager;
import com.millionaire.millionairepaymentmanager.manager.PayManager;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInvestmentController {

    private final Logger logger = LoggerFactory.getLogger(UserInvestmentController.class);

    @Autowired
    private PayManager payManager;

    @Autowired
    private PayBackManager payBackManager;

    @Autowired
    private InvestmentProductService productService;

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ReceptionUsersService receptionUsersService;

    ReceptionUsers receptionUsers = new ReceptionUsers();

    ContractResponse contractResponse = new ContractResponse();

    /**
     * 用户支付接口,跳转第三方支付页面
     *
     * @param requestBean
     * @return
     */


    @PostMapping("/u/user-investment")
    public String userInvestment(@RequestBody UserInvestmentRequestBean requestBean, HttpServletRequest servletRequest) throws IOException, FuYouException {
        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Long id = Long.valueOf(cookie.getValue());
        return payManager.payment(requestBean, id);
    }


    /**
     * 支付成功回调接口
     */
    @PostMapping("/api/back_url")
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String version = req.getParameter("VERSION");
        String type = req.getParameter("TYPE");
        String responseCode = req.getParameter("RESPONSECODE");
        String responseMsg = req.getParameter("RESPONSEMSG");
        String mchntCd = req.getParameter("MCHNTCD");
        String mchntOrderId = req.getParameter("MCHNTORDERID");
        String orderId = req.getParameter("ORDERID");
        String bankCard = req.getParameter("BANKCARD");
        String amt = req.getParameter("AMT");
        String sign = req.getParameter("SIGN");
        String key = Constants.H5_MCHNT_KEY;
        Map<String, String> keyMap = new HashMap<String, String>();
        keyMap.put(Constants.H5_MCHNT_CD, key);
//        key = keyMap.get(mchntCd);
        // 校验签名
        String signPain = new StringBuffer().append(type).append("|").append(version).append("|").append(responseCode)
                .append("|").append(mchntCd).append("|").append(mchntOrderId).append("|").append(orderId).append("|")
                .append(amt).append("|").append(bankCard).append("|").append(key).toString();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        if (MD5.MD5Encode(signPain).equals(sign)) {
            if (Constants.RESP_CODE_SUCCESS.equals(responseCode)) {
                logger.info(mchntOrderId + "支付成功~");
//                调用任务执行方法
                payBackManager.backManage(Long.valueOf(mchntOrderId));
                resp.getWriter().write("支付成功~");
                payBackManager.backManage(Long.valueOf(mchntOrderId));
            } else {
                logger.info(mchntOrderId + "支付失败~" + responseMsg);
                resp.getWriter().write("支付失败~");
            }
        } else {
            logger.info(mchntOrderId + "验签失败~");
            resp.getWriter().write("验签失败~");
        }
    }

    /**
     * 理财产品列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/app/list/products")
    public ResultBean listProducts(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {

        return new ResultBean(1, "success", productService.selectByPage(pageSize, pageNum));
    }

    /**
     * 产品详情
     *
     * @param id
     * @return
     */
    @GetMapping("/app/product/{id}")
    public ResultBean getProduct(@PathVariable("id") long id) {
        return new ResultBean(1, "success", productService.selectByPrimaryKey(id));
    }

    /**
     * 可续投投资列表数据
     */
    @GetMapping("/u/list/renewal-products")
    public ResultBean listRenewalProducts(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
//        从redis中获取到续投参数
        int investmentEnd = (int) redisTemplate.opsForValue().get("investmentEnd");
//        查询到期日期小于续投参数的用户投资，当天的不包括在内
        LocalDate now = LocalDate.now();
//        续投产品的戒指日期
        LocalDate end = now.minusDays(investmentEnd);

//        转成时间戳
        long nowTime = now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTime = end.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return new ResultBean(1, "success", investmentUserService.listRenewalInvestments(endTime, nowTime, pageSize, pageNum));
    }

    /**
     * 可续投投资详情
     */
    @GetMapping("u/renewal/investment-user/{id}")
    public ResultBean getRenewalProduct(@PathVariable("id") Long id) {

        return new ResultBean(1, "success", investmentUserService.selectRenewalInvestmentById(id));
    }

    /**
     * 获取签署合同所需信息
     *
     * @param servletRequest
     * @return
     */
    @GetMapping("u/contract-userInfo")
    public ResultBean getContractUserInfo(HttpServletRequest servletRequest) {
        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Long id = Long.valueOf(cookie.getValue());
        receptionUsers = receptionUsersService.selectByPrimaryKey(id);
        ContractResponse contractResponse = new ContractResponse();
        contractResponse.setIdName(receptionUsers.getIdName());
        contractResponse.setIdNumber(receptionUsers.getIdNumber());
        String companySeal = (String) redisTemplate.opsForValue().get("companySeal");
        contractResponse.setCompanySeal(companySeal);
        return new ResultBean(1, "success", contractResponse);
    }

    /**
     * 合同详情
     *
     * @param id 用户投资表的id传过来
     * @return
     */
    @GetMapping("u/investment-contract/{id}")
    public ResultBean getContractUser(@PathVariable("id") Long id) {
        contractResponse = investmentUserService.selectContractResponse(id);
        String companySeal = (String) redisTemplate.opsForValue().get("seal");
        contractResponse.setCompanySeal(companySeal);
        return new ResultBean(1, "success", companySeal);
    }

    /**
     * 产品续投的
     */
    @PostMapping("u/renewal-investment-user")
    public ResultBean postRenewal(@RequestBody JSONObject jsonObject) throws TimerTaskException {
        Long id = jsonObject.getLong("id");
        String contactSign = jsonObject.getString("contactSign");
        if (payManager.postRenewal(id, contactSign)) {
            return new ResultBean(1, "success");
        } else {
            return new ResultBean(-1, "application something error");
        }
    }

}
