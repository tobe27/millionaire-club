package com.millionaire.millionairequartzmanager.lkjob;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Liu Kai
 * @Description: TODO 后台消息定时发送
 * @date 2018/8/28 18:51
 */

public class MessageSendJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();

    }
}
