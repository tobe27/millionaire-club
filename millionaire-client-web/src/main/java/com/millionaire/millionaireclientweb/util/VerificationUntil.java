package com.millionaire.millionaireclientweb.util;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

@Component("verificationUntil")
public class VerificationUntil {
    @Autowired
    private ReceptionUsersService receptionUsersService;

    ReceptionUsers receptionUsers = new ReceptionUsers();

    public Map Verification(Cookie cookie) {

        /**
         * 10.用户未登录
         * 20.未实名认证
         * 30.已提交实名申请
         * 40.用户未绑定银行卡
         * 50.验证绑卡成功
         */
        Map map = new HashMap();
        if (cookie.equals(null)) {
            map.put("verificationStatus", 10);
            return map;
        }
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        //            判断用户是否实名验证
        Byte idAuthentication = receptionUsers.getIdAuthentication();
        if (idAuthentication == 10 || idAuthentication == 30 || idAuthentication == 60) {//未认证
            map.put("verificationStatus", 20);
            return map;
        }
        if (idAuthentication == 40 || idAuthentication == 50) {//已申请未审核
            map.put("verificationStatus", 30);
            return map;
        }
        if (receptionUsers.getBankId().equals(0)) {  //用户未绑定银行卡
            map.put("verificationStatus", 40);
        }
        //验证绑卡成功
        map.put("verificationStatus", 50);
        return map;
    }


}
