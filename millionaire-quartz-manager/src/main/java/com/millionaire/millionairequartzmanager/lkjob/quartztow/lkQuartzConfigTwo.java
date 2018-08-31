//package com.millionaire.millionairequartzmanager.lkjob;
//
//
//import org.quartz.CronTrigger;
//import org.quartz.Scheduler;
//import org.quartz.ee.servlet.QuartzInitializerListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
///**
// * @author Liu Kai
// * @Description: TODO
// * @date 2018/8/30 14:31
// */
//@Configuration
//public class lkQuartzConfigTwo {
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        return  new SchedulerFactoryBean();
//    }
//
//
//    @Bean
//    public CronTriggerFactoryBean cronTriggerFactoryBean(){
//
//        return new CronTriggerFactoryBean();
//    }
//
//    @Bean
//    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(){
//        return new MethodInvokingJobDetailFactoryBean();
//    }
//
//    @Bean
//    public QuartzInitializerListener executorListener() {
//        return new QuartzInitializerListener();
//    }
//
///**
// * @Description MethodInvokingJobDetailFactoryBean 和 JobDetailFactoryBean 均包含了jobdetail
// **/
////    @Bean
////    public JobDetailFactoryBean jobDetailFactoryBean(){
////        return  new JobDetailFactoryBean();
////    }
//
//    // 定义一个调度器？
////      @Bean
////    public Scheduler scheduler(){
////        return schedulerFactoryBean().getScheduler();
////    }
////
////    @Bean
////    public CronTrigger cronTrigger(){
////        return cronTriggerFactoryBean().getObject();
////    }
//
//}
