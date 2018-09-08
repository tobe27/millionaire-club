package com.millionaire.millionairequartzmanager.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class TestJob {

    public void test() {
        System.out.println("quartz任务执行");
    }
}
