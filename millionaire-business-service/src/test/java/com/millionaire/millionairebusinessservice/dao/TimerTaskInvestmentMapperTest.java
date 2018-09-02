package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TimerTaskInvestmentMapperTest {

    @Resource
    private TimerTaskInvestmentMapper timerTaskInvestmentMapper;

    @Test
    public void selectForRenewalInvestment() {
        System.out.println(timerTaskInvestmentMapper.selectIdForRenewalInvestment(5L));
    }

    @Test
    public void insert() {
        TimerTaskInvestment timerTaskInvestment = new TimerTaskInvestment();
        timerTaskInvestment.setInvestmentUserId(5L);
        timerTaskInvestment.setPaybackAmount(10233);
        timerTaskInvestment.setExecuteType((byte)0);
        timerTaskInvestment.setTimes((byte)10);
        timerTaskInvestment.setStatus((byte)10);
        timerTaskInvestment.setExecuteTime(System.currentTimeMillis());
        timerTaskInvestment.setGmtCreate(System.currentTimeMillis());
        timerTaskInvestment.setGmtUpdate(System.currentTimeMillis());
        timerTaskInvestmentMapper.insert(timerTaskInvestment);
        System.out.println(timerTaskInvestment.getId());
    }

    @Test
    public void updateTimerTaskForRenewal() {
        timerTaskInvestmentMapper.updateTimerTaskForRenewal(2000, (byte) 40, 8L, 47L);
    }
}