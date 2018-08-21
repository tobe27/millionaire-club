package com.millionaire.millionaireuserservice.controller;

import com.aliyuncs.exceptions.ClientException;
import com.millionaire.millionairecommonapi.aliyun.MessageVerification;
import com.millionaire.millionaireuserservice.module.FlowNumberGeneration;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
public class Controller {

    @Resource
    private ReceptionUsersService receptionUsersService;
    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("loginPage")
    public String loginPage(){
        return "登陆页";
    }
    @PostMapping("/login/0")
    public String login0(Long phone,String password){
        if(phone==null){
            return "手机号不能为空!";
        }
        if(password==null){
            return "密码不能为空";
        }
        ReceptionUsers receptionUsers = receptionUsersService.findByPhone(phone);
        if(receptionUsers == null){
            return "用户不存在";
        }
        String salt = receptionUsers.getSalt();
        String passwordOne = receptionUsers.getPassword();
        String passwordTwo = new Md5Hash(password,salt,2).toString();
        if(!passwordOne.equals(passwordTwo)){
            return "密码输入错误";
        }
        receptionUsers.setLoginTime(System.currentTimeMillis());
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsers.setId(receptionUsers.getId());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return "登陆成功";
    }

    @PostMapping("/login/1")
    public String login1(Long phone,String code,String password,String rePassword,String managerNumber){
        if(phone==null){
            return "手机号不能为空";
        }
        if(code.length()==0){
            return "验证码不能为空";
        }
        if(password.length()==0){
            return "密码不能为空";
        }
        if(rePassword.length()==0){
            return "重复密码不能为空";
        }
        if (!password.equals(rePassword)){
            return "两次密码输入不一致";
        }
        ReceptionUsers receptionUser = receptionUsersService.findByPhone(phone);
        if(receptionUser!=null){
            return "用户名存在！";
        }
        String salt = String.valueOf(new Random().nextInt(899999)+100000);
        String Md5HashPassword = new Md5Hash(password,salt,2).toString();
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
        if(phone==null){
            return "手机号不能为空";
        }
        Integer random = new Random().nextInt(899999)+100000;
        String key = phone.toString();
        String value = random.toString();
        System.out.println(key);
        System.out.println(value);

//        if (MessageVerification.setSendSmsResponse(key,random));{
            redisTemplate.opsForValue().set(key,value,1000*60*5);
            return "验证码发送到手机";



    }
    @PutMapping("/forgetPassword")
    public String forgetPassword(Long phone,String code,String password,String rePassword){
        if(code.length()==0){
            return "验证码不能为空";
        }
        if (password.length()==0){
            return "密码不能为空";
        }
        if (rePassword.length()==0){
            return "重复密码不能为空";
        }
        System.out.println(phone);
        String salt = (String) redisTemplate.opsForValue().get(phone.toString());
        System.out.println(salt);
        if(!salt.equals(code)){
            return "验证码错误";
        }
        if(password.equals(rePassword)){
            return "两次密码输入不一致";
        }
        ReceptionUsers receptionUsers = new ReceptionUsers();
        String Md5HashPassword = new Md5Hash(password,salt,2).toString();
        receptionUsers.setPassword(Md5HashPassword);
        receptionUsers.setSalt(salt);
        receptionUsers.setGmtUpdate(System.currentTimeMillis());
        receptionUsersService.updateByPrimaryKey(receptionUsers);
        return "更新成功";
    }
}
