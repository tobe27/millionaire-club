package com.millionaire.millionaireserverweb.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParameterSettingControllerTest {
@Resource
private RedisTemplate redisTemplate;


    @Test
    public void systemParamSetting() {
        String seal = "sealllllllllllllll";
        redisTemplate.opsForValue().set("seal",seal);


        String b = (String) redisTemplate.opsForValue().get("seal");

        System.out.println("b = " + b);


    }
}