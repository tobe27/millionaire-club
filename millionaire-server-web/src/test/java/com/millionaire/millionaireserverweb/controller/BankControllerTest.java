package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.service.BankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankControllerTest {

    @Resource
    private BankService bankService;

    @Test
    public void insertBank() {
        String[] str = {"中国","建设","农业","华夏","广州商业","南京","天津"};
        String[] str2 = {"邮政","北京"};
        for(String s :str2){
            Bank bank = new Bank();
            bank.setBankName(s+"银行");
            bank.setSingleLimit(10000.0D);
            bank.setDailyLimit(200000.0D);
            bank.setPaymentNumber("0000000");
            bank.setPresentNumber("0000000");
            bank.setBankLogo("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
            bank.setFounder("admin");
            bank.setModifier("admin");
            bankService.insert(bank);
        }
    }
}