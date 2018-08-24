package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClaimInfoControllerTest {

    @Resource
    private ClaimInfoService claimInfoService;

    @Test
    public void insertClaimInfo() {
        ClaimInfo claimInfo = new ClaimInfo();
        claimInfo.setClaimCode("fsdc2");
        claimInfo.setCreditorName("adfsdfas2");
        claimInfo.setCreditorPhone("dfasadfs2");
        claimInfo.setCreditorIdNumber("adfsdafsadsf2");
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
}