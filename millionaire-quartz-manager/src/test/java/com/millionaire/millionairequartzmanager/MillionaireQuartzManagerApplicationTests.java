package com.millionaire.millionairequartzmanager;

import com.millionaire.millionairebusinessservice.service.TimerTaskInvestmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MillionaireQuartzManagerApplicationTests {

    @Autowired
    private TimerTaskInvestmentService taskInvestmentService;

	@Test
	public void contextLoads() {

        System.out.println(taskInvestmentService.listTimerTaskForExecute());

	}

}
