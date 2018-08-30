package com.millionaire.millionairequartzmanager.dao;

import com.millionaire.millionairebusinessservice.dao.TimerTaskInvestmentMapper;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TimerTaskInvestmentMapperTest {

    @Resource
    private TimerTaskInvestmentMapper timerTaskInvestmentMapper;
    @Test
    public void insert() {
        TimerTaskInvestment timerTaskInvestment = new TimerTaskInvestment();
        timerTaskInvestment.setInvestmentUserId(2L);
        timerTaskInvestment.setPaybackAmount(500000);
        timerTaskInvestment.setExecuteType((byte)10);
        timerTaskInvestment.setTimes((byte)1);
        timerTaskInvestment.setStatus((byte)0);
        timerTaskInvestment.setExecuteTime(System.currentTimeMillis()+60*60*1000*24);
        timerTaskInvestment.setGmtCreate(System.currentTimeMillis());
        timerTaskInvestment.setGmtUpdate(System.currentTimeMillis());
        System.out.println(timerTaskInvestmentMapper.insert(timerTaskInvestment));
        System.out.println(timerTaskInvestment.getId());
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(timerTaskInvestmentMapper.selectByPrimaryKey(3L));
    }
}