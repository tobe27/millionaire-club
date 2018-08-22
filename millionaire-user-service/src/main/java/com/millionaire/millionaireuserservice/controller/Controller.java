package com.millionaire.millionaireuserservice.controller;

import com.aliyuncs.exceptions.ClientException;
import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.dao.TradingFlowMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairecommonapi.aliyun.MessageVerification;
import com.millionaire.millionairemanagerservice.dao.BankMapper;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionaireuserservice.module.FlowNumberGeneration;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
import com.millionaire.millionaireuserservice.util.CookieUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class Controller {

    @Resource
    private ReceptionUsersService receptionUsersService;
    @Resource
    private UserBankService userBankService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private BankMapper bankMapper;
    @Resource
    private TradingFlowMapper tradingFlowMapper;
    @Resource
    private InvestmentUserMapper investmentUserMapper;

    @GetMapping("loginPage")
    public String loginPage() {
        return "登陆页";
    }

    /**
     * 用户登陆
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/login/0")
    public String login0(Long phone, String password,HttpServletResponse request) {
        if (phone == null) {
            return "手机号不能为空!";
        }
        if (password == null) {
            return "密码不能为空";
        }
        ReceptionUsers receptionUsers = receptionUsersService.findByPhone(phone);
        if (receptionUsers == null) {
            return "用户不存在";
        }
        String salt = receptionUsers.getSalt();
        String passwordOne = receptionUsers.getPassword();
        String passwordTwo = new Md5Hash(password, salt, 2).toString();
        if (!passwordOne.equals(passwordTwo)) {
            return "密码输入错误";
        }
        receptionUsers.setLoginTime(System.currentTimeMillis());
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        CookieUtil.createCookie("cookie",receptionUsers.getId().toString(),request);
        return "登陆成功";
    }

    /**
     * 注册用户
     * @param phone
     * @param code
     * @param password
     * @param rePassword
     * @param managerNumber
     * @return
     */
    @PostMapping("/login/1")
    public String login1(Long phone, String code, String password, String rePassword, String managerNumber) {
        if (phone == null) {
            return "手机号不能为空";
        }
        if (code.length() == 0) {
            return "验证码不能为空";
        }
        if (password.length() == 0) {
            return "密码不能为空";
        }
        if (rePassword.length() == 0) {
            return "重复密码不能为空";
        }
        if (!password.equals(rePassword)) {
            return "两次密码输入不一致";
        }
        ReceptionUsers receptionUser = receptionUsersService.findByPhone(phone);
        if (receptionUser != null) {
            return "用户名存在！";
        }
        String salt = String.valueOf(new Random().nextInt(899999) + 100000);
        String Md5HashPassword = new Md5Hash(password, salt, 2).toString();
        ReceptionUsers receptionUsers = new ReceptionUsers();
        receptionUsers.setPhone(phone);  // 账号
        receptionUsers.setPassword(Md5HashPassword);  //密码
        receptionUsers.setSalt(salt);  //盐
        receptionUsers.setUserNumber(salt);  //用户编号
        receptionUsers.setManagerNumber(managerNumber);
        receptionUsers.setStatus((byte) 10);  //用户状态
        receptionUsers.setIdAuthentication((byte) 10); //实名状态
        receptionUsers.setGmtCreate(System.currentTimeMillis());
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsersService.insert(receptionUsers);
        receptionUsers.setUserNumber(FlowNumberGeneration.userProtocol(receptionUsers.getId()));
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return "注册成功！";
    }

    @PostMapping("/code")
    public String getCode(Long phone) throws ClientException {
        if (phone == null) {
            return "手机号不能为空";
        }
        Integer random = new Random().nextInt(899999) + 100000;
        System.out.println(random);
        MessageVerification.setSendSmsResponse(phone.toString(), random);
        redisTemplate.opsForValue().set(phone.toString(),random.toString(),1000 * 60 * 5, TimeUnit.MILLISECONDS);
        return "验证码发送到手机";
    }

    /**
     * 忘记密码通过手机找回
     * @param phone
     * @param code
     * @param password
     * @param rePassword
     * @return
     */
    @PutMapping("/forgetPassword")
    public String forgetPassword(Long phone, String code, String password, String rePassword) {
        if (code.length() == 0) {
            return "验证码不能为空";
        }
        if (password.length() == 0) {
            return "密码不能为空";
        }
        if (rePassword.length() == 0) {
            return "重复密码不能为空";
        }
        String salt = (String) redisTemplate.opsForValue().get(phone.toString());
        if (!salt.equals(code)) {
            return "验证码错误";
        }
        if (!password.equals(rePassword)) {
            return "两次密码输入不一致";
        }
        ReceptionUsers receptionUsers = receptionUsersService.findByPhone(phone);
        if(receptionUsers==null){
            return "用户名不存在";
        }
        String Md5HashPassword = new Md5Hash(password, salt, 2).toString();
        receptionUsers.setPassword(Md5HashPassword);
        receptionUsers.setSalt(salt);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return "更新成功";
    }

    /**
     * 我的页面返回个人信息
     * @param request
     * @return
     */
    @GetMapping("/u")
    public ReceptionUsers getUser(HttpServletRequest request){
        Cookie cookie = CookieUtil.getCookie("cookie",request);
        String id = cookie.getValue();
        Long uid = Long.valueOf(id);
        System.out.println(id);
        System.out.println(uid);

        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        return receptionUsers;
    }

    /**
     * 用户绑定银行
     * @param city
     * @param bankName
     * @param bankPhone
     * @param cardNumber
     * @param request
     * @return
     */
    @PostMapping("/u/banks")
    public String insertUserBank(String city,String bankName,String bankPhone,String cardNumber,HttpServletRequest request){
        Cookie cookie = CookieUtil.getCookie("cookie",request);
        String id = cookie.getValue();
        Long uid = Long.valueOf(id);
        UserBank userBank = new UserBank();
        userBank.setCity(city);
        userBank.setBankName(bankName);
        userBank.setCardNumber(cardNumber);
        userBank.setUid(uid);
        userBank.setBankPhone(bankPhone);
        userBank.setCardType("储蓄卡");
        userBank.setGmtCreate(System.currentTimeMillis());
        userBank.setGmtUpdate(System.currentTimeMillis());
        userBankService.insert(userBank);
        return "添加成功";
    }

    /**
     * 用户拥有的银行卡  //还需要改
     * @param request
     * @return
     */
    @GetMapping("/u/bank")
    public Map getBank(HttpServletRequest request){
        Cookie cookie = CookieUtil.getCookie("cookie",request);
        String id = cookie.getValue();
        Long uid = Long.valueOf(id);
        UserBank userBank = userBankService.selectByPrimaryKey(uid);
        Bank bank = bankMapper.selectByPrimaryKey(1L);
        Map map = new HashMap();
        map.put("userBank",userBank);
        map.put("bank",bank);
        return map;
    }

    /**
     * 所有银行名称
     * @return
     */
    @GetMapping("/u/banks")
    public List<Bank> getBanks(){
        List<Bank> banks = bankMapper.selectAll();
        return banks;
    }

    /**
     * 用户交易的流水
     * @param request
     * @return
     */
//    @GetMapping("/u/transaction")
//    public List<TradingFlow> findAll(HttpServletRequest request){
//
////        tradingFlowMapper.selectByPrimaryKey();
//    }


//    @GetMapping("/u/investments")
//    public List<InvestmentUser> getUserInvestments(byte investmentStatus,HttpServletRequest request){
//        Cookie cookie = CookieUtil.getCookie("cookie",request);
//        String id = cookie.getValue();
//        Long uid = Long.valueOf(id);
////        List<InvestmentUser> investmentUsers = investmentUserMapper.查询通过uid和投资状态
//
//    }

}
