package com.millionaire.millionaireclientweb.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CookieUtilTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void getCookie() {

        redisTemplate.opsForValue().set("investmentEnd",5);
        System.out.println(redisTemplate.opsForValue().get("investmentEnd"));
    }
}