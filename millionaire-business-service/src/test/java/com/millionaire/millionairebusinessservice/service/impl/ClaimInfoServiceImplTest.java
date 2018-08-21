package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.ClaimInfoMapper;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClaimInfoServiceImplTest {
    @Resource
    ClaimInfoService service;

    @Resource
  private   ClaimInfoMapper mapper;

    @Test
    public void insertSelective() {
        ClaimInfo claimInfo=new ClaimInfo();
        claimInfo.setCreditorIdNumber("201809198806089521");
        claimInfo.setCreditorName("李铁柱");
        claimInfo.setCreditorPhone("14786321223");
        claimInfo.setLendingPeriod(5);
        claimInfo.setLendingDate(new Date());
        claimInfo.setExpirationDate(new Date());
        claimInfo.setClaimCode("LTZ2");
        claimInfo.setRemarks("已公证");
        claimInfo.setInterestRate(0.18);
        claimInfo.setStatus(0);
        claimInfo.setLendingAmount(10000);
        claimInfo.setMatchAmount(0);
        claimInfo.setProperty("保证");
        claimInfo.setClaimContract("");
        claimInfo.setGmtCreate(System.currentTimeMillis());
        claimInfo.setGmtUpdate(System.currentTimeMillis());
        mapper.insert(claimInfo);
    }

    @Test
    public void selectByCode() {
    }
}