package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.dao.TimerTaskInvestmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class PayManagerTest {
    @Resource
    private TimerTaskInvestmentMapper taskInvestmentMapper;
    @Test
    public void postRenewal() {
        taskInvestmentMapper.updateTimerTaskForRenewal(5000, (byte)1, 5L, 12L);
        System.out.println(taskInvestmentMapper.selectByPrimaryKey(12L));
    }
}