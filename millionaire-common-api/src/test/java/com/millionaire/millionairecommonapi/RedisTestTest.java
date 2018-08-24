package com.millionaire.millionairecommonapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTestTest {

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void test() {
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

}