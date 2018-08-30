package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimerTaskInvestmentMapper {

    int insert(TimerTaskInvestment record);

    TimerTaskInvestment selectByPrimaryKey(Long id);
}
