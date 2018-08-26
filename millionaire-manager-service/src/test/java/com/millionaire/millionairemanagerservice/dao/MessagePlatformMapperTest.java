package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagePlatformMapperTest {
    @Resource
    MessagePlatformMapper messagePlatformMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        MessagePlatform messagePlatform = new MessagePlatform();
        messagePlatform.setTitle("title");
        messagePlatform.setSendingCrowd((byte) 0);
        messagePlatform.setBrief("brief");
        messagePlatform.setContent("content");
        messagePlatform.setType((byte) 0);
        messagePlatform.setTimingTime(System.currentTimeMillis());
        messagePlatform.setStatus((byte) 10);
        messagePlatform.setEditors("editors");
        messagePlatform.setGmtCreate(System.currentTimeMillis());
        messagePlatform.setGmtUpdate(System.currentTimeMillis());

        messagePlatformMapper.insertSelective(messagePlatform);
        System.out.println("messagePlatform = " + messagePlatform);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        MessagePlatform messagePlatform = messagePlatformMapper.selectByPrimaryKey(1L);
        System.out.println("messagePlatform = " + messagePlatform);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByQuery() {
    }
}