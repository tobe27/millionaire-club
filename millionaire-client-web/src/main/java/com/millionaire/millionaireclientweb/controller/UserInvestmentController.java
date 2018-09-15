package com.millionaire.millionaireclientweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.exception.TimerTaskException;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.ContractResponse;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import com.millionaire.millionairebusinessservice.transport.RenewalInvestmentDTO;
import com.millionaire.millionaireclientweb.result.ResultBean;
import com.millionaire.millionaireclientweb.util.CookieUtil;
import com.millionaire.millionaireclientweb.util.VerificationUntil;
import com.millionaire.millionairemanagerservice.service.ContentService;
import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.Constants;
import com.millionaire.millionairepaymentmanager.fuyou.until.MD5;
import com.millionaire.millionairepaymentmanager.manager.InstallmentRequest;
import com.millionaire.millionairepaymentmanager.manager.PayBackManager;
import com.millionaire.millionairepaymentmanager.manager.PayManager;
import com.millionaire.millionairepaymentmanager.requst.InstallmentBean;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private ContentService contentService;

    @Autowired
    private VerificationUntil verificationUntil;

    @Autowired
    private InstallmentRequest installmentRequest;

    ReceptionUsers receptionUsers = new ReceptionUsers();

    ContractResponse contractResponse = new ContractResponse();


    /**
     * TODO 测试需要暂时为get接口，需要添夹cookie验证
     *
     * @return
     * @throws IOException
     * @throws FuYouException
     */
    @GetMapping("/u/user-investment")
    public String userInvestmentT(  UserInvestmentRequestBean requestBean,
                                 @RequestParam("id") Long id,HttpServletRequest servletRequest) throws IOException, FuYouException {

        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Map map = verificationUntil.Verification(cookie);
        logger.info("入参信息"+map+requestBean);
//        对cookie信息进行检验
//        if (!map.get("verificationStatus").equals(50)) {
//            return "用户验证未成功，请跳转页面";
//        }
        int isHavingNovicePlan = 0;
        logger.info("查询用户投资,用户" + id);
        return payManager.payment(requestBean, id,isHavingNovicePlan);
    }


    @PostMapping("/u/user-investment")
    public String userInvestment(@RequestBody UserInvestmentRequestBean requestBean,
                                 HttpServletRequest servletRequest) throws IOException, FuYouException {

        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Map map = verificationUntil.Verification(cookie);
        logger.info("入参信息"+map+requestBean);
//        对cookie信息进行检验
        if (!map.get("verificationStatus").equals(50)) {
            return "用户验证未成功，请跳转页面";
        }
        int isHavingNovicePlan = (int) map.get("isHavingNovicePlan");

        long uid = (long) map.get("uid");
        logger.info("查询用户投资,用户" + uid);
        return payManager.payment(requestBean, uid,isHavingNovicePlan);
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
     * 商户接收支付结果的后台通知地址
     *
     * @return
     */
    @GetMapping("/api/home_url")
    public String homeBack() {
        return "success";
    }

    @GetMapping("/api/return_url")
    public String returnBack() {
        return "fail";
    }


    /**
     * Todo bug :产品上下架未做判断
     * 理财产品列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/app/list/products")
    public ResultBean listProducts(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return new ResultBean(1, "success", productService.listProductOnShelf(pageSize,pageNum));
    }

    /**
     * 产品详情
     *
     * @param id
     * @return
     */
    @GetMapping("/app/product/{id}")
    public ResultBean getProduct(@PathVariable("id") long id, HttpServletRequest servletRequest) {
//        用户验证状态判断
        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Map map = new HashMap();
        map.put("verificationStatus", verificationUntil.Verification(cookie));
        InvestmentProduct investmentProduct = productService.selectByPrimaryKey(id);
        logger.info("投资产品详情，产品" + id);
        List list = new ArrayList();
        list.add(map);
        list.add(investmentProduct);
        return new ResultBean(1, "success", list);
    }

    /**
     * TODO 轮播图展示（type = 10 and state = 10 ）
     * 轮播图展示
     *
     * @return
     */
    @GetMapping("app/list/banners")
    public ResultBean listBanners() {
        return new ResultBean(1, "success", contentService.listCoverShelf());
    }

    /**
     * Todo 推荐产品 is_recommend =1  and is_shelf = 1
     * 推荐产品展示
     *
     * @return
     */
    @GetMapping("app/products/recommend")
    public ResultBean getProductRecommend() {
        return new ResultBean(1, "success", productService.listProductsRecommend());
    }


    /**
     * Todo bug cookie是否为空
     * 可续投投资列表数据
     */
    @GetMapping("/u/list/renewal-products")
    public ResultBean listRenewalProducts(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, HttpServletRequest servletRequest) {
        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Map map = verificationUntil.Verification(cookie);

        int verificationStatus = (int) map.get("verificationStatus");
//        对cookie信息进行检验
        if (verificationStatus == 10) {
            return new ResultBean(-1, "用户未登录", map);
        }

        /**
         * TODO  从redis 中取值错误
         */
//        从redis中获取到续投参数
        int investmentEnd = (int) redisTemplate.opsForValue().get("investmentEnd");

        logger.info("可续投期限"+investmentEnd);
//        查询到期日期小于续投参数的用户投资，当天的不包括在内
        LocalDate now = LocalDate.now();
        logger.info("当前时间"+now);
//        续投产品的戒指日期
        LocalDate end = now.minusDays(investmentEnd);
        logger.info("查询的下标日期"+end);
//        转成时间戳
        long nowTime = now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        logger.info("当前时间戳"+nowTime);
        long endTime = end.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        logger.info("查询的下标日期时间戳"+endTime);
        Long uid = Long.valueOf(cookie.getValue());
        logger.info("查询用户可续投资,用户" + uid);

        PageInfo pageInfo = investmentUserService.listRenewalInvestments(endTime, nowTime, uid, pageSize, pageNum);

        return new ResultBean(1, "success", pageInfo);
    }

    /**
     * 可续投产品详情
     *
     * @param id 用户投资id
     * @return
     */
    @GetMapping("u/renewal/investment-user/{id}")
    public ResultBean getRenewalProduct(@PathVariable("id") Long id, HttpServletRequest servletRequest) {
        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        List list = new ArrayList();
        Map map = verificationUntil.Verification(cookie);
        RenewalInvestmentDTO renewalInvestmentDTO = investmentUserService.selectRenewalInvestmentById(id);
        list.add(map);
        list.add(renewalInvestmentDTO);
        return new ResultBean(1, "success", list);
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
     * TOdo 续投的时候需要对用户投资的状态进行判定，是否是合法的投资
     * 产品续投的
     */
    @PostMapping("u/renewal-investment-user")
    public ResultBean postRenewal(@RequestBody JSONObject jsonObject, HttpServletRequest servletRequest) throws TimerTaskException {

        Cookie cookie = CookieUtil.getCookie("cookie", servletRequest);
        Map map = verificationUntil.Verification(cookie);
//        对cookie信息进行检验
        if (!map.get("verificationStatus").equals(50)) {
            logger.info("用户验证未成功,cookie值"+cookie);
            return new ResultBean(-1, "用户验证未成功，请跳转页面", map);
        }

        Long id = jsonObject.getLong("id");
        String contactSign = jsonObject.getString("contactSign");
        int code = payManager.postRenewal(id, contactSign);
        if (code == 10001) {
            return new ResultBean(code, "新手产品用户只能购买一次");
        }
        if (code == 10002) {
            return new ResultBean(code, "application something error");
        }
        return new ResultBean(1, "success");
    }

    @GetMapping("redis/set")
    public String testRedis(@RequestParam("num") int num) {
        redisTemplate.opsForValue().set("investmentEnd", num);
        return "ok";
    }


    /**
     * @author qiaobao
     * @datetime 2018/9/6 3:00
     * @decribe 用户分期的投资计算
     */
    @GetMapping("u/installment-Calculator")
    public ResultBean installment(@RequestParam("productId") Long id, @RequestParam("amount") int amount) {
        logger.info(id+"产品利息计算"+amount);

        InstallmentBean installmentBean = installmentRequest.request(id, amount);
        return new ResultBean(1,"success",installmentBean);
    }


}



