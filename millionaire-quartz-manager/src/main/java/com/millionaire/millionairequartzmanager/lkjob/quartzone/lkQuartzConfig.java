package com.millionaire.millionairequartzmanager.lkjob.quartzone;

import com.millionaire.millionairequartzmanager.lkjob.job.TestJob;
import com.millionaire.millionairequartzmanager.lkjob.job.TestJobThree;
import com.millionaire.millionairequartzmanager.lkjob.job.TestJobTwo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/30 11:45
 */
@Configuration
public class lkQuartzConfig {


    /**
     * @Description 创建jobdetail 有两个方法，
     * 一个是MethodInvokingJobDetailFactoryBean
     * 通过setTargetObject绑定job的object
     * 通过setTargetMethod 绑定job类中指定的方法
     * 一个是JobDetailFactoryBean
     * 通过setJobClass 绑定job的class类，该类必须要实现job接口
     **/


    //定义一个job
    @Bean("jobDetailFactoryBean")
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(TestJob job) {
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean =
                new MethodInvokingJobDetailFactoryBean();
        //不允许并发
        jobDetailFactoryBean.setConcurrent(false);
        jobDetailFactoryBean.setName("myName");
        jobDetailFactoryBean.setGroup("myGroup");
        jobDetailFactoryBean.setTargetObject(job);
        jobDetailFactoryBean.setTargetMethod("testOne");
        return jobDetailFactoryBean;
    }

    /**
     * @Description 使用JobDetailFactoryBean创建多任务，会报bean重复
     **/
//    @Bean()
//    public JobDetailFactoryBean jobDetailFactoryBean(TestJobTwo jobtwo) {
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setGroup("mygroup1");
//        jobDetailFactoryBean.setName("myname1");
//        jobDetailFactoryBean.setJobClass(jobtwo.getClass());
//        return jobDetailFactoryBean;
//    }
//
//    @Bean("jobdetailthree")
//    public JobDetailFactoryBean jobDetailFactoryBean2(TestJobThree jobthree){
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setGroup("mygroup2");
//        jobDetailFactoryBean.setName("myname2");
//        jobDetailFactoryBean.setJobClass(jobthree.getClass());
//        return jobDetailFactoryBean;
//    }


    // 定义一个trigger
    @Bean("triggerFactoryBean")
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setName("myTrigger");
        triggerFactoryBean.setGroup("myGroup");
        triggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        triggerFactoryBean.setCronExpression("0/1 * * * * ?");
        return triggerFactoryBean;
    }

//    @Bean("triggerFactoryBean")
//    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobdetaitwo) {
//        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
//        triggerFactoryBean.setName("myTriggertwo");
//        triggerFactoryBean.setGroup("myGrouptwo");
//        jobdetaitwo.setJobClass(TestJobTwo.class);
//        jobdetaitwo.setName("2");
//        jobdetaitwo.setGroup("2");
//        triggerFactoryBean.setJobDetail(jobdetaitwo.getObject());
//        triggerFactoryBean.setCronExpression("0/2 * * * * ?");
//        return triggerFactoryBean;
//    }
//
//    @Bean("triggerFactoryBean2")
//    public CronTriggerFactoryBean cronTriggerFactoryBean2(JobDetailFactoryBean jobdetailthree) {
//        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
//        triggerFactoryBean.setName("myTriggerthree");
//        triggerFactoryBean.setGroup("myGroupthree");
//        jobdetailthree.setJobClass(TestJobThree.class);
//        jobdetailthree.setName("1");
//        jobdetailthree.setGroup("1");
//        triggerFactoryBean.setJobDetail(jobdetailthree.getObject());
//        triggerFactoryBean.setCronExpression("0/3 * * * * ?");
//        return triggerFactoryBean;
//    }
//

    //定义一个调度器
//    @Bean("schedulerFactoryBean")
//    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean triggerFactoryBean) {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setTriggers(triggerFactoryBean.getObject());
//        schedulerFactoryBean.setAutoStartup(true);
//        return schedulerFactoryBean;
//    }

    //定义一个调度器
//    @Bean("schedulerFactoryBean")
//    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean triggerFactoryBean,
//                                                     CronTriggerFactoryBean triggerFactoryBean2) {
//
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setTriggers(triggerFactoryBean.getObject(), triggerFactoryBean2.getObject());
////        schedulerFactoryBean.setTriggers(triggerFactoryBean.getObject());
//        schedulerFactoryBean.setAutoStartup(true);
//        return schedulerFactoryBean;
//    }


}
