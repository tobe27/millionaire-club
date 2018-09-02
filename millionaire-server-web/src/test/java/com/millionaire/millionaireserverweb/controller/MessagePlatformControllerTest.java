package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessagePlatformControllerTest {

    @Resource
    private MessagePlatformService messagePlatformService;

    @Test
    public void insertMessagePlatform() {
        for(int i = 0;i<6;i++) {
            if(i<3) {
                MessagePlatform messagePlatform = new MessagePlatform();
                messagePlatform.setTitle("认证投资人平台消息" + i);
                messagePlatform.setSendingCrowd((byte) 10);
                messagePlatform.setBrief("简述"+i);
                messagePlatform.setContent("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
                messagePlatform.setType((byte) 10);
                messagePlatform.setStatus((byte) 10);
                messagePlatform.setEditors("admin");
                messagePlatformService.insertSelective(messagePlatform);
            }else{
                MessagePlatform messagePlatform = new MessagePlatform();
                messagePlatform.setTitle("所有人平台消息" + i);
                messagePlatform.setSendingCrowd((byte) 20);
                messagePlatform.setBrief("简述"+i);
                messagePlatform.setContent("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
                messagePlatform.setType((byte) 10);
                messagePlatform.setStatus((byte) 10);
                messagePlatform.setEditors("admin");
                messagePlatformService.insertSelective(messagePlatform);
            }

        }
    }

    @Test
    public void listMessagePlatform() {
    }

    @Test
    public void selectMessage() {
    }

    @Test
    public void updateMessageStatus() {
    }

    @Test
    public void deleteMessagePlatform() {
    }
}