package com.millionaire.millionaireclientweb.util;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.transport.InvestmentUsersDTO;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;

import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CookieUtilTest {
    @Resource
    private ReceptionUsersService receptionUsersService;
//    @Resource
//    private ReceptionUsersMapper receptionUsersMapper;
    @Test
    public void getCookie() {
        UserReceptionDTO userReceptionDTO = receptionUsersService.findById(1L);
        System.out.println(userReceptionDTO);
    }
    @Resource
    private InvestmentUserService investmentUserService;
    @Test
    public void getPd(){
        InvestmentUser investmentUser = new InvestmentUser();
        investmentUser.setUid(1L);
        investmentUser.setInvestmentStatus((byte) 10);
        List<InvestmentUsersDTO> investmentUsers = investmentUserService.findByUidInvestmentStatus(investmentUser);
        System.out.println(investmentUsers);
    }
@Resource
    MessagePlatformService messagePlatformService;
    @Test
    public void res() {
        TreeMap map = new TreeMap();
        init(map);
        print(map);
    }

    @Resource
    private MessageUserService messageUserService;
    public void init(Map map) {
        List<UserMessageDTO> messageUsers = messageUserService.findByUid(1L);
        List<MessagePlatform> messagePlatformTwo = messagePlatformService.findBySendingCrowd((byte) 10);

        for (UserMessageDTO userMessageDTO:messageUsers) {
            map.put(userMessageDTO.getGmtCreate(),userMessageDTO);
        }
        for (MessagePlatform messagePlatform:messagePlatformTwo) {
            map.put(messagePlatform.getGmtCreate(),messagePlatform);
        }
    }
    public static void print(Map map) {
        Iterator<Map.Entry> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}