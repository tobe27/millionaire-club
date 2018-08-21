package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReceptionUsersControllerTest {

    @Autowired
    ReceptionUsersMapper usersMapper;

    @Autowired
    ReceptionUsersService usersService;

    @Test
    public void getListReceptionUsers() {
    }

    @Test
    public void updateUserStatus() {
    }

    @Test
    public void selectUsers() {
//        ReceptionUsers users=usersMapper.selectByPrimaryKey(8L);
//        System.out.println("users = " + users);
        ReceptionUsers users1=usersService.selectByPrimaryKey(8L);
        System.out.println("users1 = " + users1);
    }

    @Test
    public void updateUserPhoneMangerNum() {
    }

    @Test
    public void deleteBankCard() {
    }
}