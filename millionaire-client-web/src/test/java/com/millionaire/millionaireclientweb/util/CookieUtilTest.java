package com.millionaire.millionaireclientweb.util;

import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;

import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

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
}