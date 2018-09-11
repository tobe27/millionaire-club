package com.millionaire.millionaireclientweb.util;

import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.dao.MessageUserMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.transport.InvestmentUsersDTO;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import com.millionaire.millionairemanagerservice.dao.MessagePlatformMapper;
import com.millionaire.millionairemanagerservice.request.MessageQuery;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import com.millionaire.millionairemanagerservice.transport.MessagePlatformDTO;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.plaf.SpinnerUI;

import java.util.*;

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

//    @Test
//    public void res() {
//createDefinitionSortTreeMap();
//    }
//    public  void createDefinitionSortTreeMap() {
//                Map map1 = new TreeMap<Long,Object>((o1,o2)->o2.compareTo(o1));
//        TreeMap map = new TreeMap(new Comparator<Long>() {
//            @Override
//            public int compare(Long o1, Long o2) {
//                return o2.compareTo(o1);
//            }
//
//        });
//        init(map);
//        print(map);
//    }

    @Resource
    private MessageUserService messageUserService;
//    public void init(Map map) {
//        List<UserMessageDTO> messageUsers = messageUserService.findByUid(1L);
////        List<MessagePlatformDTO> messagePlatformTwo = messagePlatformService.findBySendingCrowd(messageUsers);
//
//        for (UserMessageDTO userMessageDTO:messageUsers) {
//            map.put(userMessageDTO.getGmtCreate(),userMessageDTO);
//        }
//        for (MessagePlatformDTO messagePlatform:messagePlatformTwo) {
//            map.put(messagePlatform.getGmtCreate(),messagePlatform);
//        }
//    }
//    public static void print(Map map) {
//        Iterator<Map.Entry> it = map.entrySet().iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = it.next();
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//    }

    @Resource
    private MessageUserMapper messageUserMapper;

    @Test
    public void testUserMapper() {
        System.out.println(messageUserMapper.findByLook(1L));
    }

    @Resource
    private MessagePlatformMapper messagePlatformMapper;
    @Test
    public void testMessageUserd() {
        Integer integer = messagePlatformMapper.findBySendingCrowdCount((byte) 20);
        System.out.println(integer);
        Integer integer1 = messagePlatformMapper.findBySendingCrowdCount((byte) 10);
        System.out.println(integer1);
    }

    @Test
    public void testLook() {
        MessageUser messageUser = new MessageUser();
        messageUser.setIsLook((byte) 10);
        messageUser.setGmtUpdate(System.currentTimeMillis());
        messageUser.setId(1L);
        messageUserService.updateByPrimaryKey(messageUser);
    }

    @Resource
    private InvestmentUserMapper investmentUserMapper;
    @Test
    public void user() {
        List<UserMessageDTO> messageUsers = messageUserService.findByUid((long) 10);
        messageUsers.forEach((message)-> System.out.println(message));
    }
    @Test
    public void sdfsdf() {
        MessageQuery messageQuery = new MessageQuery();
        messageQuery.setSendingCrowd((byte) 10);
        messageQuery.setId(1L);
        List<MessagePlatformDTO> messagePlatformOne = messagePlatformService.findBySendingCrowd(messageQuery);
        messagePlatformOne.forEach((a)-> System.out.println(a));
    }

    @Test
    public void pr() {
        Integer integer = messagePlatformService.findBySendingCrowdCount((byte) 10);
        Integer integer1 = messagePlatformService.findBySendingCrowdCount((byte) 20);
        Integer result = integer+integer1;
        System.out.println(result);
    }

    @Test
    public void pr1() {
        Integer integer = messageUserService.findByMessagePlatformId(1L);
        System.out.println(integer);
    }

    @Test
    public void sdfsdgf() {
        MessageUser messageUserTwo = new MessageUser();
        messageUserTwo.setIsLook((byte) 10);
        messageUserTwo.setCode((byte) 0);
        messageUserTwo.setUid(1L);
        messageUserTwo.setInvestmentUserId((long) 0);
        messageUserTwo.setMessagePlatformId(1L);
        messageUserTwo.setGmtCreate(System.currentTimeMillis());
        messageUserTwo.setGmtUpdate(System.currentTimeMillis());
        messageUserService.insetByUserMessage(messageUserTwo);
    }
}