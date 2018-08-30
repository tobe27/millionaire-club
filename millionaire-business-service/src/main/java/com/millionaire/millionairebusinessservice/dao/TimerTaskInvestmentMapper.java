package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimerTaskInvestmentMapper {

    int insert(TimerTaskInvestment record);

    TimerTaskInvestment selectByPrimaryKey(Long id);

    /**
     * 获取即将执行的投资定时任务
     */
    List<TimerTaskInvestment> listTimerTaskForExecute();
}
