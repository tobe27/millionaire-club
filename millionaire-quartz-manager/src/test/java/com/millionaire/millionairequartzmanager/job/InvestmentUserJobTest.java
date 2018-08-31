package com.millionaire.millionairequartzmanager.job;

import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.TimerTaskInvestmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestmentUserJobTest {
    @Autowired
    private TimerTaskInvestmentService taskInvestmentService;
    @Test
    public void execute1() {
        System.out.println(taskInvestmentService.listTimerTaskForExecute());
    }
}