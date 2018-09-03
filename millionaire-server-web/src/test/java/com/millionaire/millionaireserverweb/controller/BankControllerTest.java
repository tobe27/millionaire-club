package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionairemanagerservice.dao.BankMapper;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankControllerTest {

    @Resource
    private BankService service;
    @Resource
    private BankMapper mapper;
    @Test
    public void selectBankByPage() {
        BankQuery query=new BankQuery();
        query.setBankName("a");
        PageInfo<Bank> pageInfo=service.selectBankByPage(1,1,query);
        System.out.println(pageInfo);
    }

    @Test
    public void selectByPrimaryKey() {
//        Bank bank=service.selectByPrimaryKey(1L);
//        System.out.println(bank);
        Bank bank1=service.selectByPrimaryKey(1L);
        System.out.println(bank1);
    }
    @Resource
    BackstageUsersService backstageUsersService;
@Test
public void testB(){
   backstageUsersService.findAll();
}

    @Test
    public void insertBank() {
        Bank bank =new Bank();
        bank.setBankName("交通银行");
        bank.setSingleLimit(200000.0D);
        bank.setDailyLimit(200000.0D);
        bank.setPaymentNumber("12345678");
        bank.setPresentNumber("12345678");
        bank.setBankLogo("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
        bank.setFounder("admin");
        bank.setModifier("admin");
      service.insert(bank);

    }

}