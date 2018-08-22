package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankMapperTest {

    @Resource
    private BankMapper bankMapper;
    @Resource
    private BankService bankService;

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
        bank.setGmtUpdate(System.currentTimeMillis());
        bank.setFounder("DGFSGDFSDFSG");
        bank.setModifier("DGFSDFSDFSG");

       int i= bankMapper.insert(bank);
        System.out.println("i = " + i);
        System.out.println(bank.getId());
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        Bank bank=bankService.selectByPrimaryKey(1L);
        System.out.println(bank);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectBankByPage() {
        BankQuery query=new BankQuery();
        List<Bank> bankList=bankMapper.selectBankByPage(query);
        System.out.println(bankList);
    }
}