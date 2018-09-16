package com.millionaire.millionaireserverweb.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ParameterSettingControllerTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void systemParamSetting() {

        redisTemplate.opsForValue().set("seal", "https://majorjoe.oss-cn-beijing.aliyuncs.com/%E4%B8%8B%E8%BD%BD.png");

        redisTemplate.opsForValue().set("investmentEnd", 5);

    }
}