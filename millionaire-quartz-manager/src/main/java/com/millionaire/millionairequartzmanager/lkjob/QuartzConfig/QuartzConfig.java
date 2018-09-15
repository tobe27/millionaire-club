package com.millionaire.millionairequartzmanager.lkjob.QuartzConfig;

import com.millionaire.millionairequartzmanager.job.InvestmentUserJob;
import com.millionaire.millionairequartzmanager.job.TestJob;
import com.millionaire.millionairequartzmanager.lkjob.job.ClaimInfoCheckJob;
import com.millionaire.millionairequartzmanager.lkjob.job.InvestmentUserCheckJob;
import com.millionaire.millionairequartzmanager.lkjob.job.MessageSendJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
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
     * @Description 定时消息发送任务
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
     * @Description 定时消息发送trigger
     **/
    @Bean("messageSendTrigger")
    public CronTriggerFactoryBean messageSendTrigger(JobDetail messageSendJobDetail) {
        CronTriggerFactoryBean messageSendTrigger = new CronTriggerFactoryBean();
        // 添加任务
        messageSendTrigger.setJobDetail(messageSendJobDetail);
        // 添加表达式，每分钟执行一次
        messageSendTrigger.setCronExpression("0 0/1 * * * ? ");
        return messageSendTrigger;
    }


    /**
     * @Description 债权信息过期查看任务
     **/
    @Bean("claimInfoExpiredCheckJobDetail")
    public MethodInvokingJobDetailFactoryBean claimMatchExpiredCheck(ClaimInfoCheckJob claimInfoCheckJob) {
        MethodInvokingJobDetailFactoryBean claimInfoExpiredCheckJobDetail = new MethodInvokingJobDetailFactoryBean();
        // 不允许并发
        claimInfoExpiredCheckJobDetail.setConcurrent(false);
        claimInfoExpiredCheckJobDetail.setTargetObject(claimInfoCheckJob);
        claimInfoExpiredCheckJobDetail.setTargetMethod("claimInfoExpire");
        return claimInfoExpiredCheckJobDetail;
    }

    /**
     * @Description 债权信息过期查看任务trigger
     **/
    @Bean("claimInfoCheckTrigger")
    public CronTriggerFactoryBean claimInfoCheckTrigger(JobDetail claimInfoExpiredCheckJobDetail) {
        CronTriggerFactoryBean messageSendTrigger = new CronTriggerFactoryBean();
        // 添加任务
        messageSendTrigger.setJobDetail(claimInfoExpiredCheckJobDetail);
        // 添加表达式，每天 00：30 执行
        messageSendTrigger.setCronExpression("0 30 0 * * ?");
        return messageSendTrigger;
    }


    /**
     * @Description 债权到期提醒任务
     **/
    @Bean("claimInfoExpiredWarningJobDetail")
    public MethodInvokingJobDetailFactoryBean claimInfoExpiredWarningJobDetail(ClaimInfoCheckJob claimInfoCheckJob) {
        MethodInvokingJobDetailFactoryBean claimInfoExpiredCheckJobDetail = new MethodInvokingJobDetailFactoryBean();
        // 不允许并发
        claimInfoExpiredCheckJobDetail.setConcurrent(false);
        claimInfoExpiredCheckJobDetail.setTargetObject(claimInfoCheckJob);
        claimInfoExpiredCheckJobDetail.setTargetMethod("claimInfoExpireWarn");
        return claimInfoExpiredCheckJobDetail;
    }

    /**
     * @Description 债权到期提醒任务trigger
     **/
    @Bean("claimInfoWarningTrigger")
    public CronTriggerFactoryBean claimInfoWarningTrigger(JobDetail claimInfoExpiredWarningJobDetail) {
        CronTriggerFactoryBean messageSendTrigger = new CronTriggerFactoryBean();
        // 添加任务
        messageSendTrigger.setJobDetail(claimInfoExpiredWarningJobDetail);
        // 添加表达式，每天 08：00 执行
        messageSendTrigger.setCronExpression("0 0 8 * * ?");
        return messageSendTrigger;
    }


/**
 * @Description 债权投资警戒线提醒任务
 **/
@Bean("claimInfoUnmatchLineWarnJobDetail")
public MethodInvokingJobDetailFactoryBean claimInfoUnmatchLineWarn(ClaimInfoCheckJob claimInfoCheckJob){
    MethodInvokingJobDetailFactoryBean claimInfoExpiredCheckJobDetail = new MethodInvokingJobDetailFactoryBean();
    // 不允许并发
    claimInfoExpiredCheckJobDetail.setConcurrent(false);
    claimInfoExpiredCheckJobDetail.setTargetObject(claimInfoCheckJob);
    claimInfoExpiredCheckJobDetail.setTargetMethod("claimInfoUnmatchLineWarn");
    return claimInfoExpiredCheckJobDetail;
}
    /**
     * @Description 债权投资警戒线提醒任务trigger
     **/
    @Bean("claimInfoUnmatchLineWarnTrigger")
    public CronTriggerFactoryBean claimInfoUnmatchLineWarnTrigger(JobDetail claimInfoUnmatchLineWarnJobDetail) {
        CronTriggerFactoryBean messageSendTrigger = new CronTriggerFactoryBean();
        // 添加任务
        messageSendTrigger.setJobDetail(claimInfoUnmatchLineWarnJobDetail);
        // 添加表达式，每天 08：05 执行
        messageSendTrigger.setCronExpression("0 5 8 * * ?");
        return messageSendTrigger;
    }



    /**
     * @Description 用户投资到期提醒任务
     **/
    @Bean("investmentUserExpireWarnJobDetail")
    public MethodInvokingJobDetailFactoryBean investmentUserExpireWarn(InvestmentUserCheckJob investmentUserCheckJob){
        MethodInvokingJobDetailFactoryBean claimInfoExpiredCheckJobDetail = new MethodInvokingJobDetailFactoryBean();
        // 不允许并发
        claimInfoExpiredCheckJobDetail.setConcurrent(false);
        claimInfoExpiredCheckJobDetail.setTargetObject(investmentUserCheckJob);
        claimInfoExpiredCheckJobDetail.setTargetMethod("investmentUserExpireWarn");
        return claimInfoExpiredCheckJobDetail;
    }
    /**
     * @Description 用户投资到期提醒任务trigger
     **/
    @Bean("investmentUserExpireWarnTrigger")
    public CronTriggerFactoryBean investmentUserExpireWarnTrigger(JobDetail investmentUserExpireWarnJobDetail) {
        CronTriggerFactoryBean messageSendTrigger = new CronTriggerFactoryBean();
        // 添加任务
        messageSendTrigger.setJobDetail(investmentUserExpireWarnJobDetail);
        // 添加表达式，每天 08：10 执行
        messageSendTrigger.setCronExpression("0 10 8 * * ?");
        return messageSendTrigger;
    }


    /**
     *@author qiaobao
     *@datetime  2018/9/6 7:10
     *@decribe 用户投资到期的定时任务
     */

    @Bean("investmentMaturityJobDetail")
    public MethodInvokingJobDetailFactoryBean investmentUserMaturity(InvestmentUserJob investmentUserJob){
        MethodInvokingJobDetailFactoryBean investmentUserMaturityJobDetail = new MethodInvokingJobDetailFactoryBean();
        // 不允许并发
        investmentUserMaturityJobDetail.setConcurrent(false);
        investmentUserMaturityJobDetail.setTargetObject(investmentUserJob);
        investmentUserMaturityJobDetail.setTargetMethod("execute");
        return investmentUserMaturityJobDetail;
    }
    /**
     *@author qiaobao
     *@datetime  2018/9/6 7:17
     *@decribe  触发器
     */

    @Bean("investmentUserMaturityTrigger")
    public CronTriggerFactoryBean investmentUserMaturityTrigger(JobDetail investmentMaturityJobDetail) {
        CronTriggerFactoryBean investmentMaturityTrigger = new CronTriggerFactoryBean();
        // 添加任务
        investmentMaturityTrigger.setJobDetail(investmentMaturityJobDetail);
        // 添加表达式，每天 凌晨1:00 执行
        investmentMaturityTrigger.setCronExpression("0 0 1 * * ?");
        return investmentMaturityTrigger;
    }



    @Bean("testJobDetail")
    public MethodInvokingJobDetailFactoryBean test(TestJob testJob){
        MethodInvokingJobDetailFactoryBean investmentUserMaturityJobDetail = new MethodInvokingJobDetailFactoryBean();
        // 不允许并发
        investmentUserMaturityJobDetail.setConcurrent(false);
        investmentUserMaturityJobDetail.setTargetObject(testJob);
        investmentUserMaturityJobDetail.setTargetMethod("test");
        return investmentUserMaturityJobDetail;
    }
    /**
     *@author qiaobao
     *@datetime  2018/9/6 7:17
     *@decribe  触发器
     */

    @Bean("testTrigger")
    public CronTriggerFactoryBean testTrigger(JobDetail testJobDetail) {
        CronTriggerFactoryBean testTrigger = new CronTriggerFactoryBean();
        // 添加任务
        testTrigger.setJobDetail(testJobDetail);
        // 添加表达式，每天 凌晨1:00 执行
        testTrigger.setCronExpression("0/1 * * * * ?");
        return testTrigger;
    }



    /**
     * @Description 定义调度器启动trigger
     **/
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTrigger messageSendTrigger,
                                                     CronTrigger claimInfoCheckTrigger,
                                                     CronTrigger claimInfoWarningTrigger,
                                                     CronTrigger claimInfoUnmatchLineWarnTrigger,
                                                     CronTrigger investmentUserExpireWarnTrigger,
                                                     CronTrigger investmentUserMaturityTrigger,
                                                     CronTrigger testTrigger) {


        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        //添加trigger
        scheduler.setTriggers(messageSendTrigger, claimInfoCheckTrigger,
                claimInfoWarningTrigger,claimInfoUnmatchLineWarnTrigger,
                investmentUserExpireWarnTrigger,investmentUserMaturityTrigger,testTrigger);
        // 设置延迟启动
        scheduler.setStartupDelay(5);
        //自动启动
        scheduler.setAutoStartup(true);
        return scheduler;
    }


}
