package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Bank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankMapperTest {

    @Resource BankMapper bankMapper;
    @Test
    public void deleteByPrimaryKey() {

    }

    @Test
    public void insert() {
        Bank bank = new Bank();

        bank.setBankName("DSFDFS");
        bank.setSingleLimit(0.156D);
        bank.setDailyLimit(0.15996D);
        bank.setPaymentNumber("ADFSAF");
        bank.setPresentNumber("ASDFADF");
        bank.setBankLogo("AFDSASDF");
        bank.setGmtCreate(System.currentTimeMillis());

        bank.setFounder("DGFSGDFSDFSG");
        bank.setModifier("DGFSDFSDFSG");

        bankMapper.insert(bank);
        System.out.println(bank.getId());
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