package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;

import java.util.List;

public interface TimerTaskInvestmentService {
    Long insert(TimerTaskInvestment record);

    /**
     * 获取即将执行的投资定时任务
     */
    List<TimerTaskInvestment> listTimerTaskForExecute();


}
