package com.millionaire.millionaireclientweb.util;

import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
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

    @Autowired
    private InvestmentUserService investmentUserService;



    public Map Verification(Cookie cookie) {

        /**
         * verificationStatus
         * 10.用户未登录
         * 20.未实名认证
         * 30.已提交实名申请
         * 40.用户未绑定银行卡
         * 50.验证绑卡成功
         */
        Map map = new HashMap();
        if (cookie==null) {
            map.put("verificationStatus", 10);
            return map;
        }
        Long uid = Long.valueOf(cookie.getValue());
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);
        //            判断用户是否实名验证
        Byte idAuthentication = receptionUsers.getIdAuthentication();

//        是否购买过新手计划  0没有 1有
        int isHavingNovicePlan = investmentUserService.selectExistNovicePlan(uid);

        if (idAuthentication == 10 || idAuthentication == 30 || idAuthentication == 60) {//未认证
            map.put("verificationStatus", 20);
            map.put("isHavingNovicePlan", isHavingNovicePlan);
            return map;
        }
        if (idAuthentication == 40 || idAuthentication == 50) {//已申请未审核
            map.put("verificationStatus", 30);
            map.put("isHavingNovicePlan", isHavingNovicePlan);
            return map;
        }
        if (receptionUsers.getBankId().equals(0)) {  //用户未绑定银行卡
            map.put("verificationStatus", 40);
            map.put("isHavingNovicePlan", isHavingNovicePlan);
        }
        //验证绑卡成功
        map.put("verificationStatus", 50);
        map.put("isHavingNovicePlan", isHavingNovicePlan);
        return map;
    }

}
