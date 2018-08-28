package com.millionaire.millionaireuserservice.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReceptionUsersMapperTest {

    @Resource
    private ReceptionUsersMapper receptionUsersMapper;
    @Test
    public void updateUserAssets() {
        int a = receptionUsersMapper.updateUserAssets(8L, 200000, System.currentTimeMillis());
        System.out.println(a);
    }

    @Test
    public void selectUserAssets() {
        System.out.println(receptionUsersMapper.selectUserAssets(8L));
    }

    @Test
    public void updateUserProfit() {
        int a = receptionUsersMapper.updateUserProfit(8L,2000,System.currentTimeMillis());
        System.out.println(a);
    }

    @Test
    public void selectUserProfit() {
        System.out.println(receptionUsersMapper.selectUserProfit(8L));
    }
}