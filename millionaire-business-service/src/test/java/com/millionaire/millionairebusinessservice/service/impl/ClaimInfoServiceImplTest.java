package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimInfoServiceImplTest {

    @Autowired
    private ClaimInfoService claimInfoService;
    @Test
    public void insert() {
        ClaimInfo claimInfo = new ClaimInfo();
        claimInfo.setClaimCode("fsdc1");
        claimInfo.setCreditorName("adfsdfas1");
        claimInfo.setCreditorPhone("dfasadfs1");
        claimInfo.setCreditorIdNumber("adfsdafsadsf1");
        claimInfo.setLendingDate(System.currentTimeMillis());
        claimInfo.setLendingPeriod(30);
        claimInfo.setLendingAmount(100000);
        claimInfo.setProperty("adsdas");
        claimInfo.setInterestRate(0.25D);
        claimInfo.setRemarks("sdds");
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(claimInfoService.insert(claimInfo));
        System.out.println("===========================================邪魔退散===========================================");
    }

    @Test
    public void selectByCode() {
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(claimInfoService.selectByCode("fsdc"));
        System.out.println("===========================================邪魔退散===========================================");
    }
}