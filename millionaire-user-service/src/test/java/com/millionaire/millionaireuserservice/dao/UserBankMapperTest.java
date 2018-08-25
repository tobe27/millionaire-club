package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.UserBank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserBankMapperTest {

    @Resource UserBankMapper userBankMapper;
    @Test
    public void deleteByPrimaryKey() {

    }

    @Test
    public void insert() {
//        UserBank userBank = new UserBank();
//        userBank.setName("GFDGFD");
//        userBank.setIdNumber("GFDSSGFD");
//        userBank.setCity("GFSGFSD");
//        userBank.setBankName("DGFSDGFS");
//        userBank.setCardNumber("DGFSDGFS");
//        userBank.setCardType("GSD");
//        userBank.setBankPhone("GSDDGF");
//        userBank.setUid(2l);
//        userBank.setGmtCreate(System.currentTimeMillis());
//        userBankMapper.insert(userBank);
//        System.out.println(userBank.getId());
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}