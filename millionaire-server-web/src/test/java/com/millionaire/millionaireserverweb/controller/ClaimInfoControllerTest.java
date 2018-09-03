package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Validated
public class ClaimInfoControllerTest {

    @Resource
    private ClaimInfoService claimInfoService;

    @Test
    public void insertClaimInfo() {
        for(int i =0;i<5;i++) {
            ClaimInfo claimInfo = new ClaimInfo();
            claimInfo.setClaimCode("ClaimCode"+i);
            claimInfo.setCreditorName("债权人"+i);
            claimInfo.setCreditorPhone("1370000010"+i);
            claimInfo.setCreditorIdNumber("36060219990101111"+i);
            claimInfo.setLendingDate(System.currentTimeMillis());
            claimInfo.setLendingPeriod(20*i);
            claimInfo.setLendingAmount(i*100000);
            claimInfo.setProperty("保证");
            claimInfo.setInterestRate(0.05*i);
            claimInfo.setRemarks("已公证");
            System.out.println("===========================================佛主保佑===========================================");
            System.out.println(claimInfoService.insert(claimInfo));
            System.out.println("===========================================邪魔退散===========================================");
        }
    }

    @Test
    public void selectClaimExpireCheck() {

        List<ClaimInfo> list =claimInfoService.selectClaimExpireCheck();
        System.out.println("list = " + list);
    }

    @Test
    public void claimTest() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ClaimInfo claimInfo =claimInfoService.selectByPrimaryKey(1L);
        System.out.println(claimInfo.getLendingPeriod());
        System.out.println(sf.format(new Date(claimInfo.getLendingDate())));
        System.out.println(sf.format(new Date(claimInfo.getExpirationDate())));

    }
}