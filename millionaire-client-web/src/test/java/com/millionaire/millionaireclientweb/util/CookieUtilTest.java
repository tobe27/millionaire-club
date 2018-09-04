package com.millionaire.millionaireclientweb.util;

import com.millionaire.millionairebusinessservice.dao.MessageUserMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.transport.InvestmentUsersDTO;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import com.millionaire.millionairemanagerservice.dao.MessagePlatformMapper;
import com.millionaire.millionairemanagerservice.dao.MessageUserPlatformMapper;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.module.MessageUserPlatform;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
    //        Map map1 = new TreeMap<Long,Object>((o1,o2)->o2.compareTo(o1));
    @Test
    public void res() {
createDefinitionSortTreeMap();
    }
    public  void createDefinitionSortTreeMap() {
        TreeMap map = new TreeMap(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }

        });
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
    @Resource
    private MessageUserPlatformMapper messageUserPlatformMapper;

    @Test
    public void testMessageUser() {
        messageUserPlatformMapper.findAll();
    }

    @Test
    public void testMessage() {
        MessageUserPlatform messageUserPlatform = new MessageUserPlatform();
        messageUserPlatform.setUid(1L);
        messageUserPlatform.setMessagePlatformId(1L);
        messageUserPlatform.setLook((byte) 10);
        messageUserPlatform.setGmtCreate(System.currentTimeMillis());
        messageUserPlatform.setGmtUpdate(System.currentTimeMillis());
        messageUserPlatformMapper.insert(messageUserPlatform);
        System.out.println(messageUserPlatform.getId());
    }
    @Resource
    private MessageUserMapper messageUserMapper;

    @Test
    public void testUserMapper() {
        System.out.println(messageUserMapper.findByLook());
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
    public void testUpdate() {
        MessageUserPlatform messageUserPlatform = new MessageUserPlatform();
        messageUserPlatform.setUid(1L);
        messageUserPlatform.setMessagePlatformId(1L);
        messageUserPlatform.setLook((byte) 20);
        messageUserPlatform.setGmtCreate(System.currentTimeMillis());
        messageUserPlatform.setGmtUpdate(System.currentTimeMillis());
        messageUserPlatform.setId(1L);
        messageUserPlatformMapper.updateById(messageUserPlatform);
        System.out.println(messageUserPlatform.getId());
    }

    @Test
    public void testSelect() {
        System.out.println(messageUserPlatformMapper.findById(1L));
    }

    @Test
    public void deleteByid() {
        messageUserPlatformMapper.deleteById(2L);
    }

    @Test
    public void testCount() {
        System.out.println(messageUserPlatformMapper.findByUidCount(1L));
    }

    @Test
    public void testLook() {
        MessageUser messageUser = new MessageUser();
        messageUser.setIsLook((byte) 10);
        messageUser.setGmtUpdate(System.currentTimeMillis());
        messageUser.setId(1L);
        messageUserService.updateByPrimaryKey(messageUser);
    }
}