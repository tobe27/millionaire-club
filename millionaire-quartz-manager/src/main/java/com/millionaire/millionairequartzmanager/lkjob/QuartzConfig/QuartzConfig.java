package com.millionaire.millionairequartzmanager.lkjob.QuartzConfig;

import com.millionaire.millionairequartzmanager.lkjob.job.MessageSendJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/31 19:42
 */
@Configuration
public class QuartzConfig {

    /**
     * @Description 配置消息发送定时任务的job detail
     **/
    @Bean("messageSendJobDetail")
    public MethodInvokingJobDetailFactoryBean messageSendJobDetail(MessageSendJob messageSendJob) {
        MethodInvokingJobDetailFactoryBean messageSendJobDetail = new MethodInvokingJobDetailFactoryBean();
        // 不允许并发
        messageSendJobDetail.setConcurrent(false);
        //添加任务
        messageSendJobDetail.setTargetObject(messageSendJob);
        //添加需要调度的方法
        messageSendJobDetail.setTargetMethod("sendMessage");
        return messageSendJobDetail;
    }

    /**
     * @Description 消息发送的trigger
     **/
    @Bean("messageSendTrigger")
    public CronTriggerFactoryBean messageSendTrigger(MethodInvokingJobDetailFactoryBean messageSendJobDetail) {
        CronTriggerFactoryBean messageSendTrigger = new CronTriggerFactoryBean();
        // 添加任务
        messageSendTrigger.setJobDetail(messageSendJobDetail.getObject());
        // 添加表达式，每分钟执行一次
        messageSendTrigger.setCronExpression("0 0/1 * * * ? ");
        return messageSendTrigger;
    }

//    List<CronTriggerFactoryBean> triggerFactoryBeanList = new ArrayList<>();


    /**
     * @Description  定义调度器启动trigger
     **/
    @Bean
    public SchedulerFactoryBean scheduler(CronTriggerFactoryBean messageSendTrigger) {
              SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
              scheduler.setTriggers(messageSendTrigger.getObject());
              scheduler.setAutoStartup(true);
              return scheduler;
    }


}
