package com.millionaire.millionaireclientweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.InvestmentUsersDTO;
import com.millionaire.millionairebusinessservice.transport.UserInvestmentDTO;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import com.millionaire.millionaireclientweb.result.ResultBean;
import com.millionaire.millionaireclientweb.util.BankUtil;
import com.millionaire.millionaireclientweb.util.CookieUtil;
import com.millionaire.millionaireclientweb.util.FlowNumberGeneration;
import com.millionaire.millionairecommonapi.aliyun.MessageVerification;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.MessageQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import com.millionaire.millionairemanagerservice.service.ContentService;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import com.millionaire.millionairemanagerservice.service.ProposalService;
import com.millionaire.millionairemanagerservice.transport.MessagePlatformDTO;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
import com.millionaire.millionaireuserservice.transport.UserBanksDTO;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class SunController {

    @Resource
    private ReceptionUsersService receptionUsersService;
    @Resource
    private UserBankService userBankService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private BankService bankService;
    @Resource
    private TradingFlowService tradingFlowService;
    @Resource
    private InvestmentUserService investmentUserService;
    @Resource
    private MessageUserService messageUserService;
    @Resource
    private MessagePlatformService messagePlatformService;
    @Resource
    private ContentService contentService;
    @Resource
    private ProposalService proposalService;

    @Autowired
    private MessageVerification messageVerification;

    Logger logger = LoggerFactory.getLogger(SunController.class);

    @GetMapping("loginPage")
    public String loginPage() {
        return "登陆页";
    }

    /**
     * 用户登陆
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/login/0")
    public ResultBean login0(@RequestBody JSONObject jsonObject, HttpServletResponse request) {
        Long phone = jsonObject.getLong("phone");
        String password = jsonObject.getString("password");
        if (password == null) {
            return new ResultBean(-1, "密码没有传");
        }
        if (phone == null) {
            return new ResultBean(-1, "请输入手机号");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "请输入密码");
        }
        String  regex = "^[0-9]{11}";
        if(!phone.toString().matches(regex)){
            return new ResultBean(-1,"手机号格式不正确");
        }
        String  regexOne = "^[a-zA-Z0-9_]{4,20}";
        if(!password.matches(regexOne)){
            return new ResultBean(-1,"密码格式不正确");
        }
        ReceptionUsers receptionUsers = receptionUsersService.findByPhone(phone);
        if (receptionUsers == null) {
            return new ResultBean(-1, "账号错误");
        }
        Byte status = receptionUsers.getStatus();
        if (status == 20) {
            return new ResultBean(-1, "该用户已被冻结");
        }
        String salt = receptionUsers.getSalt();
        String passwordOne = receptionUsers.getPassword();
        String passwordTwo = new Md5Hash(password, salt, 2).toString();
        if (!passwordOne.equals(passwordTwo)) {
            return new ResultBean(-1, "密码输入错误");
        }
        receptionUsers.setLoginTime(System.currentTimeMillis());
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        CookieUtil.createCookie("cookie", receptionUsers.getId().toString(), request);
        return new ResultBean(1, "登陆成功");
    }

    /**
     * 注册用户
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/login/1")
    public ResultBean login1(@RequestBody JSONObject jsonObject) {
        Long phone = jsonObject.getLong("phone");
        String code = jsonObject.getString("code");
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
        String managerNumber = jsonObject.getString("managerNumber");
        if (phone == null) {
            return new ResultBean(-1, "请输入手机号");
        }
        if (code == null) {
            return new ResultBean(-1, "验证码没传");
        }
        if (password == null) {
            return new ResultBean(-1, "密码没传");
        }
        if (rePassword == null) {
            return new ResultBean(-1, "重复密码没传");
        }
        if (managerNumber == null) {
            return new ResultBean(-1, "理财经理工号没传");
        }
        if (code.length() == 0) {
            return new ResultBean(-1, "请输入验证码");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "请输入密码");
        }
        if (rePassword.length() == 0) {
            return new ResultBean(-1, "请再次输入密码");
        }
        if (!password.equals(rePassword)) {
            return new ResultBean(-1, "两次密码输入不一致");
        }
        String  regex = "^[0-9]{11}";
        if(!phone.toString().matches(regex)){
            return new ResultBean(-1,"手机号格式不正确");
        }
        String  regexOne = "^[a-zA-Z0-9_]{4,20}";
        if(!password.matches(regexOne)){
            return new ResultBean(-1,"密码格式不正确");
        }
        ReceptionUsers receptionUser = receptionUsersService.findByPhone(phone);
        if (receptionUser != null) {
            return new ResultBean(-1, "用户名已存在");
        }
        String redisCode = (String) redisTemplate.opsForValue().get(phone.toString());
        if (redisCode == null) {
            return new ResultBean(-1, "请获得验证码");
        }
        if (!code.equals(redisCode)) {
            return new ResultBean(-1, "验证码错误");
        }
        String salt = String.valueOf(new Random().nextInt(899999) + 100000);
        String Md5HashPassword = new Md5Hash(password, salt, 2).toString();
        ReceptionUsers receptionUsers = new ReceptionUsers();
        receptionUsers.setPhone(phone);  // 账号
        receptionUsers.setPassword(Md5HashPassword);  //密码
        receptionUsers.setSalt(salt);  //盐
        receptionUsers.setUserNumber(salt);  //用户编号
        receptionUsers.setManagerNumber(managerNumber);
        receptionUsers.setAssets(0);
        receptionUsers.setProfit(0);
        receptionUsers.setBankId(0L);
        receptionUsers.setLogo("https://majorjoe.oss-cn-beijing.aliyuncs.com/%E4%B8%8B%E8%BD%BD.jpg");
        receptionUsers.setStatus((byte) 10);  //用户状态
        receptionUsers.setIdAuthentication((byte) 10); //实名状态
        receptionUsers.setGmtCreate(System.currentTimeMillis());
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsersService.insert(receptionUsers);
        receptionUsers.setUserNumber(FlowNumberGeneration.userProtocol(receptionUsers.getId()));
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return new ResultBean(1, "注册成功");
    }

    /**
     * 发送验证码到手机
     *
     * @param jsonObject
     * @return
     * @throws ClientException
     */
    @PostMapping("/code")
    public ResultBean getCode(@RequestBody JSONObject jsonObject) throws ClientException {
        Long phone = jsonObject.getLong("phone");
        if (phone == null) {
            return new ResultBean(-1, "请输入手机号");
        }
        String  regex = "^[0-9]{11}";
        if(!phone.toString().matches(regex)){
            return new ResultBean(-1,"手机号格式不正确");
        }
        Integer random = new Random().nextInt(899999) + 100000;
        System.out.println(random);
        messageVerification.setSendSmsResponse(phone.toString(), random);
        redisTemplate.opsForValue().set(phone.toString(), random, 1000 * 60 * 5, TimeUnit.MILLISECONDS);
        return new ResultBean(1, "发送成功");
    }

    /**
     * 用户协议图片地址
     *
     * @return
     */
    @GetMapping("/agreement")
    public ResultBean getAgreement() {
        String agreement = (String) redisTemplate.opsForValue().get("userAgreement");
        return new ResultBean(1, "用户协议", agreement);
    }

    /**
     * 忘记密码通过手机找回
     *
     * @param jsonObject
     * @return
     */
    @PutMapping("/forgetPassword")
    public ResultBean forgetPassword(@RequestBody JSONObject jsonObject) {
        Long phone = jsonObject.getLong("phone");
        String code = jsonObject.getString("code");
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
        if (phone == null) {
            return new ResultBean(-1, "请输入手机号");
        }
        if (code == null) {
            return new ResultBean(-1, "验证码没传");
        }
        if (password == null) {
            return new ResultBean(-1, "密码没传");
        }
        if (rePassword == null) {
            return new ResultBean(-1, "重复密码没传");
        }
        if (code.length() == 0) {
            return new ResultBean(-1, "请输入验证码");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "请输入密码");
        }
        if (rePassword.length() == 0) {
            return new ResultBean(-1, "请再次确认密码）");
        }
        String salt = (String) redisTemplate.opsForValue().get(phone.toString());
        if (salt == null) {
            return new ResultBean(-1, "请获得验证码");
        }
        if (!salt.equals(code)) {
            return new ResultBean(-1, "验证码错误");
        }
        if (!password.equals(rePassword)) {
            return new ResultBean(-1, "两次密码输入不一致");
        }
        String  regex = "^[0-9]{11}";
        if(!phone.toString().matches(regex)){
            return new ResultBean(-1,"手机号格式不正确");
        }
        String  regexOne = "^[a-zA-Z0-9_]{4,20}";
        if(!password.matches(regexOne)){
            return new ResultBean(-1,"密码格式不正确");
        }
        ReceptionUsers receptionUsers = receptionUsersService.findByPhone(phone);
        if (receptionUsers == null) {
            return new ResultBean(-1, "用户名不存在");
        }
        String Md5HashPassword = new Md5Hash(password, salt, 2).toString();
        receptionUsers.setPassword(Md5HashPassword);
        receptionUsers.setSalt(salt);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return new ResultBean(1, "更新成功");
    }

    /**
     * 我的页面返回个人信息
     *
     * @param request
     * @return
     */
    @GetMapping("/u")
    public ResultBean getUser(HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long id = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(id);
        return new ResultBean(1, "返回个人信息", receptionUsers);
    }

    /**
     * 用户添加银行
     * @param jsonObject
     * @param request
     * @return
     */
    @PostMapping("/u/banks")
    public ResultBean insertUserBank(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String city = jsonObject.getString("city");
        String bankName = jsonObject.getString("bankName");
        String bankPhone = jsonObject.getString("bankPhone");
        String cardNumber = jsonObject.getString("cardNumber");
        if (city == null) {
            return new ResultBean(-1, "城市没传");
        }
        if (bankName == null) {
            return new ResultBean(-1, "银行名称没传");
        }
        if (bankPhone == null) {
            return new ResultBean(-1, "银行预留手机没传");
        }
        if (cardNumber == null) {
            return new ResultBean(-1, "银行卡号没传");
        }
        if (city.length() == 0) {
            return new ResultBean(-1, "城市不能为空");
        }
        if (bankName.length() == 0) {
            return new ResultBean(-1, "银行名称不能为空");
        }
        if (bankPhone.length() == 0) {
            return new ResultBean(-1, "银行预留手机号不能为空");
        }
        if (cardNumber.length() == 0) {
            return new ResultBean(-1, "银行卡号不能为空");
        }
        String  regex = "^[0-9]{11}";
        if(!bankPhone.matches(regex)){
            return new ResultBean(-1,"手机号格式不正确");
        }
        String a = BankUtil.getNameOfBank(cardNumber);
        int size = a.indexOf("·");
        String name = a.substring(0,size);
        if(!name.equals(bankName)){
            return new ResultBean(-1,"银行卡号与开户行不匹配");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        Byte authentication = receptionUsers.getIdAuthentication();
        if (authentication != 20) {
            return new ResultBean(-1, "未实名认证");
        }
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
        return new ResultBean(1, "添加成功");
    }
//    @GetMapping("/u/bankPage")
//    public ResultBean getBankPage(HttpServletRequest request){
//        Cookie cookie = CookieUtil.getCookie("cookie", request);
//        Long uid = Long.valueOf(cookie.getValue());
//        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
//        Byte authentication = receptionUsers.getIdAuthentication();
//        if(authentication!=20){
//            return new ResultBean(-1,"请先进行实名认证");
//        }
//        return new ResultBean(1,"跳转表单页面");
//    }

    /**
     * 用户拥有的银行卡
     *
     * @param request
     * @return
     */
    @GetMapping("/u/bank")
    public ResultBean getBank(HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long id = Long.valueOf(cookie.getValue());
        List<UserBanksDTO> userBanks = userBankService.findById(id);
        return new ResultBean(1, "返回用户的银行卡信息", userBanks);
    }

    /**
     * 所有银行名称
     *
     * @return
     */
    @GetMapping("/u/banks")
    public ResultBean getBanks() {
        List<Bank> banks = bankService.selectAll();
        return new ResultBean(1, "返回所有银行信息", banks);
    }

    /**
     * 用户交易的流水
     *
     * @param request
     * @return
     */
    @GetMapping("/u/transaction")
    public ResultBean findAll(Integer pageNum, HttpServletRequest request) {
        if (pageNum == null) {
            return new ResultBean(-1, "请输入pageNum");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        PageHelper.startPage(pageNum, 5);
        List<TradingFlow> tradingFlows = tradingFlowService.findByUid(uid);
        PageInfo pageInfo = new PageInfo(tradingFlows);
        return new ResultBean(1, "用户交易的流水", pageInfo);
    }

    /**
     * 交易流水详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/u/transaction/{id}")
    public ResultBean findById(@PathVariable Long id) {
        TradingFlow tradingFlow = tradingFlowService.selectByPrimaryKey(id);
        return new ResultBean(1, "交易流水详情", tradingFlow);
    }


    /**
     * 查询用户投资通过状态
     *
     * @param investmentStatus
     * @param request
     * @return
     */
    @GetMapping("/u/investments")
    public ResultBean getUserInvestments(Integer pageNum, Byte investmentStatus, HttpServletRequest request) {
        if (pageNum == null) {
            return new ResultBean(-1, "pageNum没传");
        }

            if (investmentStatus == null) {
                return new ResultBean(-1, "投资状态不能为空");
            }
            Cookie cookie = CookieUtil.getCookie("cookie", request);
            Long uid = Long.valueOf(cookie.getValue());
            InvestmentUser investmentUser = new InvestmentUser();
            investmentUser.setUid(uid);
            investmentUser.setInvestmentStatus(investmentStatus);
            PageHelper.startPage(pageNum, 5);
            List<InvestmentUsersDTO> investmentUsers = investmentUserService.findByUidInvestmentStatus(investmentUser);
            PageInfo pageInfo = new PageInfo(investmentUsers);
            return new ResultBean(1, "通过用户传来的投资状态查询", pageInfo);
        }



    /**
     * 投资详情
     *
     * @param id
     * @return
     */
    @GetMapping("/u/investment/{id}")
    public ResultBean getById(@PathVariable Long id) {
        UserInvestmentDTO userInvestmentDTO = investmentUserService.findById(id);
        if (userInvestmentDTO == null) {
            return new ResultBean(-1, "用户投资不存在");
        }
        if (userInvestmentDTO.getLook() != 10) {
            InvestmentUser investmentUser = new InvestmentUser();
            investmentUser.setLook((byte) 10);
            investmentUser.setGmtUpdate(System.currentTimeMillis());
            investmentUser.setId(id);
            investmentUserService.updateById(investmentUser);
        }
        return new ResultBean(1, "获取用户投资详情", userInvestmentDTO);
    }

    /**
     * 消息中心
     *
     * @param request
     * @return
     */
    @GetMapping("/u/message")
    public ResultBean getMessage(HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        Map map = new TreeMap<Long, Object>((o1, o2) -> o2.compareTo(o1));
        logger.info("查询个人消息!");
        List<UserMessageDTO> messageUsers = messageUserService.findByUid(uid);
        for (UserMessageDTO userMessageDTO : messageUsers) {
            map.put(userMessageDTO.getGmtCreate(), userMessageDTO);
        }
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        MessageQuery messageQuery = new MessageQuery();
        messageQuery.setId(uid);
        byte authentication = receptionUsers.getIdAuthentication();
        if (authentication == 20) {
            logger.info("查询实名认证后的平台消息!");
            messageQuery.setSendingCrowd((byte) 20);
            List<MessagePlatformDTO> messagePlatformOne = messagePlatformService.findBySendingCrowd(messageQuery);
            for (MessagePlatformDTO messagePlatformDTO : messagePlatformOne) {
                if (messagePlatformDTO.getLook() == null) {
                    map.put(messagePlatformDTO.getGmtCreate(), messagePlatformDTO);
                }
                if (messagePlatformDTO.getLook() != null) {
                    if (messagePlatformDTO.getLook() == 10) {
                        map.put(messagePlatformDTO.getGmtCreate(), messagePlatformDTO);
                    }
                }
            }
            messageQuery.setSendingCrowd((byte) 10);
            List<MessagePlatformDTO> messagePlatformTwo = messagePlatformService.findBySendingCrowd(messageQuery);
            for (MessagePlatformDTO messagePlatformDTO : messagePlatformTwo) {
                if (messagePlatformDTO.getLook() == null) {
                    map.put(messagePlatformDTO.getGmtCreate(), messagePlatformDTO);
                }
                if (messagePlatformDTO.getLook() != null) {
                    if (messagePlatformDTO.getLook() == 10) {
                        map.put(messagePlatformDTO.getGmtCreate(), messagePlatformDTO);
                    }
                }
            }
            return new ResultBean(1, "用户投资详情", map);
        }
        logger.info("查询未实名认证后的平台消息");
        messageQuery.setSendingCrowd((byte) 20);
        List<MessagePlatformDTO> messagePlatform = messagePlatformService.findBySendingCrowd(messageQuery);
        for (MessagePlatformDTO messagePlatformDTO : messagePlatform) {
            if (messagePlatformDTO.getLook() == null) {
                map.put(messagePlatformDTO.getGmtCreate(), messagePlatformDTO);
            }
            if (messagePlatformDTO.getLook() != null) {
                if (messagePlatformDTO.getLook() == 10) {
                    map.put(messagePlatformDTO.getGmtCreate(), messagePlatformDTO);
                }
            }
        }
        return new ResultBean(1, "用户投资详情", map);
    }

    /**
     * 消息中心长图片
     *
     * @param id
     * @return
     */
    @GetMapping("/u/message/{id}")
    public ResultBean getMessagePlatform(@PathVariable Long id, HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        MessageUser messageUser = new MessageUser();
        messageUser.setUid(uid);
        messageUser.setMessagePlatformId(id);
        MessageUser messageUserOne = messageUserService.findByUserMessage(messageUser);
        if (messageUserOne == null) {
            MessageUser messageUserTwo = new MessageUser();
            messageUserTwo.setIsLook((byte) 10);
            messageUserTwo.setCode((byte) 0);
            messageUserTwo.setUid(uid);
            messageUserTwo.setInvestmentUserId((long) 0);
            messageUserTwo.setMessagePlatformId(id);
            messageUserTwo.setGmtCreate(System.currentTimeMillis());
            messageUserTwo.setGmtUpdate(System.currentTimeMillis());
            messageUserService.insetByUserMessage(messageUserTwo);
        }
        return new ResultBean(1, "获取消息中心", messagePlatformService.selectByPrimaryKey(id));
    }

    /**
     * 用户删除平台消息
     *
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/u/messagePlatform/{id}")
    public ResultBean deleteMessage(@PathVariable Long id, HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        MessageUser messageUser = new MessageUser();
        messageUser.setUid(uid);
        messageUser.setMessagePlatformId(id);
        MessageUser messageUserOne = messageUserService.findByUserMessage(messageUser);
        if (messageUserOne == null) {
            MessageUser messageUserTwo = new MessageUser();
            messageUserTwo.setIsLook((byte) 20);
            messageUserTwo.setCode((byte) 0);
            messageUserTwo.setUid(uid);
            messageUserTwo.setInvestmentUserId((long) 0);
            messageUserTwo.setMessagePlatformId(id);
            messageUserTwo.setGmtCreate(System.currentTimeMillis());
            messageUserTwo.setGmtUpdate(System.currentTimeMillis());
            messageUserService.insetByUserMessage(messageUserTwo);
        }
        MessageUser message = new MessageUser();
        message.setIsLook((byte) 20);
        message.setGmtUpdate(System.currentTimeMillis());
        message.setId(messageUserOne.getId());
        messageUserService.updateByUserMessage(message);
        return new ResultBean(1, "删除成功");
    }

    /**
     * 查看用户消息详细信息时访问，更新新look状态
     *
     * @param id
     * @return
     */
    @PutMapping("/u/message/{id}")
    public ResultBean updateMessage(@RequestBody JSONObject jsonObject, @PathVariable Long id) {
        Byte look = jsonObject.getByte("look");
        if (look == null) {
            return new ResultBean(-1, "查看状态没传");
        }
        MessageUser messageUser = new MessageUser();
        messageUser.setId(id);
        messageUser.setIsLook(look);
        messageUser.setGmtUpdate(System.currentTimeMillis());
        messageUserService.updateByUserMessage(messageUser);
        return new ResultBean(1, "请求成功");
    }


    /**
     * 用户获得红点数
     *
     * @param request
     * @return
     */
    @GetMapping("/u/redCount")
    public ResultBean getRedCount(HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        Integer userRed = messageUserService.findByLook(uid);  //用户红点数
        Integer c = messageUserService.findByMessagePlatformId(uid); //用户处理红点数
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        if (receptionUsers.getIdAuthentication() == 20) {
            logger.info("查询实名认证后的平台消息条数!");
            Integer a = messagePlatformService.findBySendingCrowdCount((byte) 10);  //实名
            Integer b = messagePlatformService.findBySendingCrowdCount((byte) 20);  //未实名
            Integer result = a + b + userRed - c;
            return new ResultBean(1, "请求成功", result);
        }
        Integer b = messagePlatformService.findBySendingCrowdCount((byte) 20);
        Integer result = b + userRed - c;
        return new ResultBean(1, "请求成功", result);
    }


    /**
     * 用户设置
     *
     * @param request
     * @return
     */
    @GetMapping("/u/setting")
    public ResultBean getSetting(HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        UserReceptionDTO userReceptionDTO = receptionUsersService.findById(uid);
        return new ResultBean(1, "请求成功", userReceptionDTO);
    }

    /**
     * 更换默认银行卡
     *
     * @param jsonObject
     * @param request
     * @return
     */
    @PutMapping("/u/reBank")
    public ResultBean updateReBank(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Long id = jsonObject.getLong("id");
        if (id == null) {
            return new ResultBean(-1, "默认银行id不能为空");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        receptionUsers.setBankId(id);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(uid);
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return new ResultBean(1, "更新银行卡成功");
    }

    /**
     * 邮件
     *
     * @param jsonObject
     * @param request
     * @return
     */
    @PutMapping("/u/email")
    public ResultBean updateEmail(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String email = jsonObject.getString("email");
        if (email == null) {
            return new ResultBean(-1, "邮箱没有传");
        }
        if (email.length() == 0) {
            return new ResultBean(-1, "邮件不能为空");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        receptionUsers.setEmail(email);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(uid);
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return new ResultBean(1, "更新邮箱成功");
    }

    /**
     * 地址
     *
     * @param jsonObject
     * @param request
     * @return
     */
    @PutMapping("/u/address")
    public ResultBean updateAddress(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String address = jsonObject.getString("address");
        if (address == null) {
            return new ResultBean(-1, "地址没有传");
        }
        if (address.length() == 0) {
            return new ResultBean(-1, "地址不能为空");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        receptionUsers.setAddress(address);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(uid);
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return new ResultBean(1, "更新地址成功");
    }

    /**
     * 用户提交实名
     *
     * @param jsonObject
     * @param request
     * @return
     */
    @PutMapping("/u/authentication")
    public ResultBean updateAuthentication(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String idName = jsonObject.getString("idName");
        String idNumber = jsonObject.getString("idNumber");
        String idFront = jsonObject.getString("idFront");
        String idBack = jsonObject.getString("idBack");
        if(idName==null){
            return new ResultBean(-1,"真实姓名没传");
        }
        if(idNumber==null){
            return new ResultBean(-1,"身份证信息没传");
        }
        if(idFront==null){
            return new ResultBean(-1,"身份证正面url没传");
        }
        if(idBack==null){
            return new ResultBean(-1,"身份证反面url没传");
        }
        if (idName.length() == 0) {
            return new ResultBean(-1, "真实姓名不能为空");
        }
        if (idNumber.length() == 0) {
            return new ResultBean(-1, "身份证号不能为空");
        }
        if (idFront.length() == 0) {
            return new ResultBean(-1, "身份证正面照片不能为空");
        }
        if (idBack.length() == 0) {
            return new ResultBean(-1, "身份证反面照片不能为空");
        }
        String regex = "[\\u4e00-\\u9fa5]";
        if(!idName.matches(regex)){
            return new ResultBean(-1,"请输入中文");
        }
        String regexOne = "^((\\d{18})|([0-9x]{18})|([0-9X]{18}))";
        if(!idNumber.matches(regexOne)){
            return new ResultBean(-1,"身份证格式不正确");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        if (receptionUsers.getIdAuthentication() == 10) {
            receptionUsers.setIdName(idName);
            receptionUsers.setIdNumber(idNumber);
            receptionUsers.setIdFront(idFront);
            receptionUsers.setIdBack(idBack);
            receptionUsers.setIdAuthentication((byte) 40);
            receptionUsers.setApplicationTime(System.currentTimeMillis());
            receptionUsers.setGmtUpdate(System.currentTimeMillis());
            receptionUsers.setId(uid);
            receptionUsersService.updateByPrimaryKey(receptionUsers);
            logger.info("第一次提交实名认证");
        } else {
            receptionUsers.setIdName(idName);
            receptionUsers.setIdNumber(idNumber);
            receptionUsers.setIdFront(idFront);
            receptionUsers.setIdBack(idBack);
            receptionUsers.setIdAuthentication((byte) 50);
            receptionUsers.setApplicationTime(System.currentTimeMillis());
            receptionUsers.setGmtUpdate(System.currentTimeMillis());
            receptionUsers.setId(uid);
            receptionUsersService.updateByPrimaryKey(receptionUsers);
            logger.info("多次提交实名认证");
        }
        return new ResultBean(1, "提交实名成功");
    }

    /**
     * 用户修改密码
     *
     * @param
     * @param request
     * @return
     */
    @PutMapping("/u/password")
    public ResultBean updatePassword(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) {
        String oldPassword = jsonObject.getString("oldPassword");
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
        if(oldPassword==null){
            return new ResultBean(-1,"旧密码没传");
        }
        if(password==null){
            return new ResultBean(-1,"密码没传");
        }
        if(rePassword==null){
            return new ResultBean(-1,"重复密码没传");
        }
        if (oldPassword.length() == 0) {
            return new ResultBean(-1, "旧密码不能为空");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "密码不能为空");
        }
        if (rePassword.length() == 0) {
            return new ResultBean(-1, "重复密码不能为空");
        }
        if (!password.equals(rePassword)) {
            return new ResultBean(-1, "两次密码输入不一致");
        }

        String  regex = "^[a-zA-Z0-9_]{4,20}";
        if(!oldPassword.matches(regex)){
            return new ResultBean(-1,"旧密码格式不正确");
        }
        if(!password.matches(regex)){
            return new ResultBean(-1,"密码格式不正确");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        String saltOld = receptionUsers.getSalt();
        String passwordOld = receptionUsers.getPassword();
        String Md5HashPassword = new Md5Hash(oldPassword, saltOld, 2).toString();
        if (!Md5HashPassword.equals(passwordOld)) {
            return new ResultBean(-1, "旧密码错误");
        }
        String salt = String.valueOf(new Random().nextInt(899999) + 100000);
        String newPassword = new Md5Hash(password, salt, 2).toString();
        receptionUsers.setPassword(newPassword);
        receptionUsers.setSalt(salt);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        logger.info("用户修改密码");
        CookieUtil.deleteCookie("cookie", "delete", response);
        return new ResultBean(1, "修改密码成功");
    }

    /**
     * 查询帮助需要判断类型和是否上线
     */
    @GetMapping("/u/help")
    public ResultBean getHelp() {
        Content content = contentService.findByType((byte) 20);
        return new ResultBean(1, "请求成功", content);
    }

    /**
     * 查询关于我们需要判断类型和是否上线
     *
     * @return
     */
    @GetMapping("/u/company")
    public ResultBean getCompany() {
        Content content = contentService.findByType((byte) 30);
        return new ResultBean(1, "内容中的关于我们", content);
    }

    /**
     * 用户提交意见
     *
     * @param jsonObject
     * @param request
     * @return
     */
    @PostMapping("/u/proposal")
    public ResultBean insertProposal(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String proposal = jsonObject.getString("proposal");
        if (proposal == null) {
            return new ResultBean(-1, "意见没有传");
        }
        if (proposal.length() == 0) {
            return new ResultBean(-1, "内容不能为空");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        Proposal proposals = new Proposal();
        proposals.setUid(uid);
        proposals.setProposal(proposal);
        proposals.setGmtCreate(System.currentTimeMillis());
        proposals.setGmtUpdate(System.currentTimeMillis());
        proposalService.insert(proposals);
        return new ResultBean(1, "提交成功");
    }

    /**
     * 用户退出
     * @param response
     * @return
     */
    @GetMapping("/u/esc")
    public ResultBean getEsc(HttpServletResponse response){
        CookieUtil.deleteCookie("cookie", "delete", response);
        return new ResultBean(1,"退出成功");
    }

    /**
     * 启动页
     * @return
     */
    @GetMapping("/start")
    public ResultBean getImg(){
        List img = new ArrayList();
        img.add("https://majorjoe.oss-cn-beijing.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180828210819.png");
        img.add("https://majorjoe.oss-cn-beijing.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180828210829.png");
        img.add("https://majorjoe.oss-cn-beijing.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180828210837.png");
        img.add("https://majorjoe.oss-cn-beijing.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180828210849.png");
        return new ResultBean(1,"请求成功",img);
    }
    /**
     *
     */
    @GetMapping("/checkLogin")
    public ResultBean checkLogin(HttpServletRequest request){
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        if(cookie==null){
            return new ResultBean(-1,"没有登陆");
        }
        return new ResultBean(1,"登陆了");
    }
}
