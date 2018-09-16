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

        for(int i =0;i<3;i++){
            ClaimInfo claimInfo = new ClaimInfo();
            claimInfo.setClaimCode("债权"+i);
            claimInfo.setCreditorName("债权人"+i);
            claimInfo.setCreditorPhone("13705678890");
            claimInfo.setCreditorIdNumber("123456789009876544");
            claimInfo.setLendingDate(System.currentTimeMillis());
            claimInfo.setLendingPeriod(0);
            claimInfo.setLendingAmount(10000*i);
            claimInfo.setProperty("保证");
            claimInfo.setInterestRate(0.1D*i);
            claimInfo.setRemarks("已公证");
            claimInfoService.insertSelective(claimInfo);
        }




    }
}