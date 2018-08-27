package com.millionaire.millionairebusinessservice.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageUserMapperTest {

    @Resource
    private MessageUserMapper messageUserMapper;
    @Test
    public void updateMessageUserCode() {
        int a = messageUserMapper.updateMessageUserCode(9L, (byte)10,System.currentTimeMillis());
        System.out.println(a);
    }
}