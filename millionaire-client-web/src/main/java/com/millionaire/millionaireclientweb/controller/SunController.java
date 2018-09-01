package com.millionaire.millionaireclientweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.dao.MessageUserMapper;
import com.millionaire.millionairebusinessservice.dao.TradingFlowMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.transport.UserInvestmentDTO;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import com.millionaire.millionaireclientweb.result.ResultBean;
import com.millionaire.millionaireclientweb.util.CookieUtil;
import com.millionaire.millionaireclientweb.util.FlowNumberGeneration;
import com.millionaire.millionairecommonapi.aliyun.MessageVerification;
import com.millionaire.millionairemanagerservice.dao.BankMapper;
import com.millionaire.millionairemanagerservice.dao.ContentMapper;
import com.millionaire.millionairemanagerservice.dao.MessagePlatformMapper;
import com.millionaire.millionairemanagerservice.dao.ProposalMapper;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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
public class SunController {

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
    @Resource
    private InvestmentProductMapper investmentProductMapper;
    @Resource
    private MessageUserMapper messageUserMapper;
    @Resource
    private MessagePlatformMapper messagePlatformMapper;
    @Resource
    private ContentMapper contentMapper;
    @Resource
    private ProposalMapper proposalMapper;
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
        ReceptionUsers receptionUsers = receptionUsersService.findByPhone(phone);
        if (receptionUsers == null) {
            return new ResultBean(-1, "账号错误");
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
        receptionUsers.setStatus((byte) 0);  //用户状态
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
        Integer random = new Random().nextInt(899999) + 100000;
        System.out.println(random);
        MessageVerification.setSendSmsResponse(phone.toString(), random);
        redisTemplate.opsForValue().set(phone.toString(), "123456", 1000 * 60 * 5, TimeUnit.MILLISECONDS);
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
     *
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
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
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
        List<UserBank> userBanks = userBankService.findById(id);
        return new ResultBean(1, "返回用户的银行卡信息", userBanks);
    }

    /**
     * 所有银行名称
     *
     * @return
     */
    @GetMapping("/u/banks")
    public ResultBean getBanks() {
        List<Bank> banks = bankMapper.selectAll();
        return new ResultBean(1, "返回所有银行信息", banks);
    }

    /**
     * 用户交易的流水
     *
     * @param request
     * @return
     */
    @GetMapping("/u/transaction")
    public ResultBean findAll(HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        List<TradingFlow> tradingFlows = tradingFlowMapper.findByUid(uid);
        return new ResultBean(1, "用户交易的流水", tradingFlows);
    }

    /**
     * 交易流水详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/u/transaction/{id}")
    public ResultBean findById(@PathVariable Long id) {
        TradingFlow tradingFlow = tradingFlowMapper.selectByPrimaryKey(id);
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
    public ResultBean getUserInvestments(Byte investmentStatus, HttpServletRequest request) {
        if (investmentStatus == null) {
            return new ResultBean(-1, "投资状态不能为空");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        InvestmentUser investmentUser = new InvestmentUser();
        investmentUser.setUid(uid);
        investmentUser.setInvestmentStatus(investmentStatus);
        List<InvestmentUser> investmentUsers = investmentUserMapper.findByUidInvestmentStatus(investmentUser);
        return new ResultBean(1, "通过用户传来的投资状态查询", investmentUsers);
    }

    /**
     * 投资详情
     *
     * @param id
     * @return
     */
    @GetMapping("/u/investment/{id}")
    public ResultBean getById(@PathVariable Long id) {
        UserInvestmentDTO userInvestmentDTO = investmentUserMapper.findById(id);
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
        Map map = new HashMap();
        logger.info("查询个人消息!");
        List<UserMessageDTO> messageUsers = messageUserMapper.findByUid(uid);
        map.put("messageUsers", messageUsers);
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        byte authentication = receptionUsers.getIdAuthentication();
        if (authentication == 20) {
            logger.info("查询实名认证后的平台消息!");
            List<MessagePlatform> messagePlatformOne = messagePlatformMapper.findBySendingCrowd((byte) 20);
            List<MessagePlatform> messagePlatformTwo = messagePlatformMapper.findBySendingCrowd((byte) 10);
            map.put("messagePlatformOne", messagePlatformOne);
            map.put("messagePlatformTwo", messagePlatformTwo);
            return new ResultBean(1, "用户投资详情", map);
        }
        logger.info("查询未实名认证后的平台消息");
        List<MessagePlatform> messagePlatform = messagePlatformMapper.findBySendingCrowd((byte) 20);
        map.put("messagePlatforms", messagePlatform);
        return new ResultBean(1, "用户投资详情", map);
    }

    /**
     * 消息中心长图片
     *
     * @param id
     * @return
     */
    @GetMapping("/u/message/{id}")
    public ResultBean getMessagePlatform(@PathVariable Long id) {
        return new ResultBean(1, "获取消息中心", messagePlatformMapper.selectByPrimaryKey(id));
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
        String idBank = jsonObject.getString("idBank");
        if (idName.length() == 0) {
            return new ResultBean(-1, "真实姓名不能为空");
        }
        if (idNumber.length() == 0) {
            return new ResultBean(-1, "身份证号不能为空");
        }
        if (idFront.length() == 0) {
            return new ResultBean(-1, "身份证正面照片不能为空");
        }
        if (idBank.length() == 0) {
            return new ResultBean(-1, "身份证反面照片不能为空");
        }
        Cookie cookie = CookieUtil.getCookie("cookie", request);
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        if (receptionUsers.getIdAuthentication() == 10) {
            receptionUsers.setIdName(idName);
            receptionUsers.setIdNumber(idNumber);
            receptionUsers.setIdFront(idFront);
            receptionUsers.setIdBack(idBank);
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
            receptionUsers.setIdBack(idBank);
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
    public ResultBean updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String oldPassword = jsonObject.getString("oldPassword");
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
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
        return new ResultBean(1, "修改密码成功");
    }

    /**
     * 查询帮助需要判断类型和是否上线
     */
    @GetMapping("/u/help")
    public ResultBean getHelp() {
        Content content = contentMapper.findByType((byte) 20);
        return new ResultBean(1, "请求成功", content);
    }

    /**
     * 查询关于我们需要判断类型和是否上线
     *
     * @return
     */
    @GetMapping("/u/company")
    public ResultBean getCompany() {
        Content content = contentMapper.findByType((byte) 30);
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
        proposalMapper.insert(proposals);
        return new ResultBean(1, "提交成功");
    }

}
