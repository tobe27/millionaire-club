package com.millionaire.millionairequartzmanager.lkjob.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/30 20:56
 */
@Component
public class TestJobThree implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("======test job three=====================");
    }
}
