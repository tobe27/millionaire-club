package com.millionaire.millionaireserverweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import com.millionaire.millionairebusinessservice.transport.UserTradingFlowDTO;
import com.millionaire.millionaireserverweb.result.ResultBean;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.request.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.request.UsersVerificationQuery;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
import com.millionaire.millionaireuserservice.transport.ReceptionUsersDTO;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.ErrorListener;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 后台投资用户管理 查询 修改
 * @date 2018/8/20 13:38
 */
@RestController
@RequestMapping("/a")
public class ReceptionUsersController {
    private Logger logger = LoggerFactory.getLogger(ReceptionUsersController.class);
    @Resource
    private ReceptionUsersService usersService;

    @Resource
    private TradingFlowService flowService;

    @Resource
    private InvestmentUserService investmentUserService;

    @Resource
    private UserBankService userBankService;

    /**
     * @param pageNum    页码
     * @param pageSize   每页大小
     * @param usersQuery 查询参数
     * @return 成功0 失败-1
     * @Description 根据条件查询投资用户
     **/
    @GetMapping("/list/user")
    public ResultBean getListReceptionUsers(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             ReceptionUsersQuery usersQuery) {

            PageInfo<ReceptionUsers> pageInfo =
                    usersService.selectReceptionUserByPage(usersQuery, pageSize, pageNum);
            logger.info("查询投资用户参数：{}", usersQuery);
            return new ResultBean(1, "success", pageInfo);
    }

    /**
     * @Description 查看用户实名列表
     **/
    @GetMapping("/list/user-verification")
    public ResultBean listUsersVerification(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             UsersVerificationQuery usersVerificationQuery) {

            PageInfo<ReceptionUsers> pageInfo =
                    usersService.selectUserVerificationByPage(pageSize, pageNum, usersVerificationQuery);
            logger.info("查询投资用户参数：{}", usersVerificationQuery);
            return new ResultBean(1, "success", pageInfo);

    }

    /**
     * @param uid 用户id
     * @return 成功0 失败-1
     * @Description 查询用户详细信息
     **/
    @GetMapping("/user/{uid}")
    public ResultBean selectUsers(@PathVariable("uid") Long uid) {


        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        } else {
            logger.info("查看投资用户id:{}", uid);
            return new ResultBean(1, "success", users);
        }
    }

    /**
     * @param uid 用户id
     * @return 成功0 失败-1
     * @Description 查询用户实名信息
     **/
    @GetMapping("/user-verification/{uid}")
    public ResultBean selectUsersVerification(@PathVariable("uid") Long uid) {
        ReceptionUsersDTO users = usersService.selectByID(uid);
//        List<UserBank> bankId=usersService.
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        } else {
            logger.info("查看投资用户id:{}", uid);
            return new ResultBean(1, "success", users);
        }
    }

    /**
     * @param uid    用户id
     @RequestParam("status") Byte status
     * @return 成功0 失败-1
     * @Description 用户账户状态修改
     **/
    @PutMapping("/user-status/{uid}")
    public ResultBean updateUserStatus(@PathVariable("uid") Long uid,
                                      @RequestBody JSONObject jsonObject) {
        Byte status = jsonObject.getByte("status");

        if(status != 10 && status !=20){
            return new ResultBean(-1, "error status", status);
        }
        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        } else {
            users.setStatus(status);
            usersService.updateByPrimaryKeySelective(users);
            logger.info("更新用户id{},账户冻结状态:{}", uid, status);
            return new ResultBean(1, "success change status",status);
        }
    }


    /**
     * @param uid        用户id
     @RequestParam(value = "phone", required = false) String phone,
     @RequestParam(value = "managerNumber", required = false) String managerNum
     * @return 成功0 失败-1
     * @Description 修改用户信息 手机号 产品经理工号
     **/
    @PutMapping("/user/{uid}")
    public ResultBean updateUserPhoneMangerNum(@PathVariable("uid") Long uid,
                                              @RequestBody JSONObject jsonObject) {

        String phone = jsonObject.getString("phone");
        String managerNum = jsonObject.getString("managerNumber");


        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        }
        if(phone == null && managerNum == null){
            return new ResultBean(-1,"success no change");
        }
        if (managerNum != null) {
            users.setManagerNumber(managerNum);
        }
        //手机号正则式判断
        String pattern = "1[0-9]{10}";
        if(phone != null){
            if (phone.matches(pattern)) {
                users.setPhone(Long.valueOf(phone));
            } else {
                return new ResultBean(-1, "error phonenum", phone);
            }
        }
        usersService.updateByPrimaryKeySelective(users);
        logger.info("修改用户信息 用户id:{},手机号:{},经理工号:{}", uid, phone, managerNum);
        return new ResultBean(1, "success", users.getId());

    }

    /**
     * @Description 取消用户实名状态
     * 删除用户真实姓名 用户身份证号及银行卡
     * 用户身份证url设为""
     **/
    @PutMapping("/user-verification-cancel/{uid}")
    public ResultBean cancelUserVerification(@PathVariable("uid") Long id) {
        ReceptionUsers users = usersService.selectByPrimaryKey(id);
        if (users == null) {
            return new ResultBean(-1, "error id");
        }
        // 用户实名状态改为已取消
        users.setIdAuthentication((byte) 60);
        // 删除用户真实姓名及身份证 默认银行id设为0
        users.setIdName("");
        users.setIdNumber("");
        users.setBankId(0L);
        // 用户身份证正反面url设为空字符串''
        users.setIdFront("");
        users.setIdBack("");

        // 删除用户绑定的银行卡
        userBankService.deleteByUID(id);
        logger.info("删除用户银行卡绑定 用户id:{}", id);
        usersService.updateByPrimaryKeySelective(users);
        logger.info("取消用户实名状态 用户id:{}", id);
        return new ResultBean(1, "success", users.getIdAuthentication());
    }


    /**

     * @return 成功0 失败-1
     * @Description 修改用户实名状态 修改实名认证状态接口
     *   @RequestParam("code") byte code,
     *     @RequestParam(value = "refusal", required = false) String refusal
     **/
    @PutMapping("/user-verification/{uid}")
    public ResultBean updateAuthentication(@PathVariable("uid") Long uid,
                                           @RequestBody JSONObject jsonObject) {

        byte code = jsonObject.getByte("code");
        String refusal = jsonObject.getString("refusal");


        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such id", uid);
        }
        // code= 0 审核不通过
        if (code == 0) {
            // 用户实名认证状态修改为已拒绝
            users.setIdAuthentication((byte) 30);
            users.setRefusal(refusal);
            usersService.updateByPrimaryKeySelective(users);
            logger.info("拒绝用户实名申请 用户id：{} 实名状态:{}", uid,users.getIdAuthentication());
            return new ResultBean(1, "success ", users.getIdAuthentication());
        }
        //code =1 审核通过
        if (code == 1) {
            //用户实名状态为已认证
            users.setIdAuthentication((byte) 20);
            usersService.updateByPrimaryKeySelective(users);
            logger.info("通过用户实名认证 id：{}", uid);
            return new ResultBean(1, "success", users.getIdAuthentication());
        } else {
            return new ResultBean(-1, "error code should be 0 or 1", code);
        }
    }

    /**
     * @param uid        用户id
     * @param cardNumber 银行卡
     * @return 成功0 失败-1
     * @Description 删除绑定银行卡
     **/
    @DeleteMapping("/user-bank/{uid}")
    public ResultBean deleteUserBankCard(@PathVariable("uid") Long uid,
                                         @RequestParam("cardNumber") String cardNumber) {
        //根据uid查询投资用户
        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such id", uid);
        }
        //根据投资用户id查询用户绑定银行卡信息
        List<UserBank> userBankList = userBankService.selectByUID(uid);
        // 循环去除list中的cardNum和传入的cardNum进行比对
        for (UserBank userBank : userBankList) {
            String cardNumCheck = userBank.getCardNumber();
            //存在相同的银行卡号码，删除
            if (cardNumber.equals(cardNumCheck)) {
                usersService.deleteByCardNum(cardNumber);
                logger.info("id:{} 删除绑定银行卡:{} ", uid, cardNumber);
                return new ResultBean(1, "success", uid);
            }
        }
        logger.info("删除绑定银行卡错误id:{},cardNum:{}",uid,cardNumber);
        return new ResultBean(-1, "error no such cardNumber", cardNumber);
    }


    /**
     * @Description 用户交易信息查询
     **/
    @GetMapping("/list/trading-flow/{uid}")
    public ResultBean listUserTradingFlow(@PathVariable("uid") Long uid,
                                          @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                           TradingFlowQuery query) {
        logger.info("查询交易流水uid:{},请求参数:{}", uid,query);
        query.setUid(uid);
        PageInfo<UserTradingFlowDTO> pageInfo = flowService.selectTradingFlowBypage(pageNum, pageSize, query);
        return new ResultBean(1, "success", pageInfo);
    }

    /**
     * @Description 用户投资信息列表
     **/
    @GetMapping("/list/investment-user/{uid}")
    public ResultBean listUserInvestment(@PathVariable("uid") Long uid,
                                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                          InvestmentUserQuery query) {
        query.setUid(uid);
        logger.info("查询参数:{}",query);
        PageHelper.startPage(pageNum, pageSize);
        List<InvestmentUserDTO> investmentUserDTOList = investmentUserService.listInvestmentUserByQuery(query);
        PageInfo<InvestmentUserDTO> pageInfo = new PageInfo<>(investmentUserDTOList);
        logger.info("查询用户投资信息列表 id：{}",uid);
        return new ResultBean(1, "success", pageInfo);
    }
}
