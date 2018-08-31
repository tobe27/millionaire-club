package com.millionaire.millionairequartzmanager.lkjob.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author Liu Kai
 * @Description: TODO 测试job
 * @date 2018/8/28 18:51
 */
@Component
@Configuration
@EnableScheduling
public class TestJob {



    public void  testOne(){

        System.out.println("======quartz====test=====one=============");
    }
    public void testTwo(){

        System.out.println("=======quartz=====test=====two============");
    }


}
