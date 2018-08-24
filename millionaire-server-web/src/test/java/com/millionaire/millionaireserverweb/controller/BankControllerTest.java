package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.dao.BankMapper;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
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

    }


    @Test
    public void mytest() {
//        Bank bank=service.sbank)electByPrimaryKey(1L);
//        System.out.println(;
        Bank bank=service.selectByPrimaryKey(1L);
        System.out.println(bank);
    }
    @Test
    public void dummy() {
        mapper.deleteByPrimaryKey(1L);
    }
}