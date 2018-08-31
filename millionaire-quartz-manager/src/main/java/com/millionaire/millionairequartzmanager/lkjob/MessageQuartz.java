//package com.millionaire.millionairequartzmanager.lkjob;
//
//import org.quartz.CronTrigger;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.stereotype.Component;
//
//
//import javax.annotation.Resource;
//
///**
// * @author Liu Kai
// * @Description: TODO
// * @date 2018/8/30 14:40
// */
//@Component
//@EnableScheduling
//public class MessageQuartz {
////
//    @Resource
//    private SchedulerFactoryBean schedulerFactoryBean;
//
//    @Resource
//    private CronTriggerFactoryBean cronTriggerFactoryBean;
//
//    @Resource
//    private MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean;
//
//    @Resource
//    private MessageSendJob messageSendJob;
//
////    @Resource
////    private JobDetailFactoryBean jobDetailFactoryBean;
//
//    public void messageQuartzOne() {
//
//
//        methodInvokingJobDetailFactoryBean.setConcurrent(false);
//        methodInvokingJobDetailFactoryBean.setName("Name1");
//        methodInvokingJobDetailFactoryBean.setGroup("Group1");
//        methodInvokingJobDetailFactoryBean.setTargetObject(messageSendJob);
//        methodInvokingJobDetailFactoryBean.setTargetMethod("testOne");
//
//
//        cronTriggerFactoryBean.setName("TriggerOne");
//        cronTriggerFactoryBean.setGroup("Group1");
//        cronTriggerFactoryBean.setCronExpression("0/1 * * * * ?");
//        cronTriggerFactoryBean.setJobDetail(methodInvokingJobDetailFactoryBean.getObject());
//
//
//        schedulerFactoryBean.setJobDetails(methodInvokingJobDetailFactoryBean.getObject());
//        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
//                schedulerFactoryBean.setStartupDelay(10);
//        schedulerFactoryBean.setAutoStartup(true);
//
//    }
//
////    public void messageQuartzTwo() {
////        methodInvokingJobDetailFactoryBean.setConcurrent(false);
////        methodInvokingJobDetailFactoryBean.setName("Name2");
////        methodInvokingJobDetailFactoryBean.setGroup("Group2");
////        methodInvokingJobDetailFactoryBean.setTargetObject(messageSendJob);
////        methodInvokingJobDetailFactoryBean.setTargetMethod("testTwo");
////
////
////        cronTriggerFactoryBean.setName("Trigger2");
////        cronTriggerFactoryBean.setGroup("Group2");
////        cronTriggerFactoryBean.setCronExpression("0/2 * * * * ?");
////        cronTriggerFactoryBean.setJobDetail(methodInvokingJobDetailFactoryBean.getObject());
////
////
////        schedulerFactoryBean.setJobDetails(methodInvokingJobDetailFactoryBean.getObject());
////        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
////        schedulerFactoryBean.setStartupDelay(3);
////        schedulerFactoryBean.setAutoStartup(true);
////
////    }
//
//}
