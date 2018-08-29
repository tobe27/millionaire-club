package com.millionaire.millionairequartzmanager.dao;

import com.millionaire.millionairequartzmanager.module.TimerTaskInvestment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimerTaskInvestmentMapper {

    int insert(TimerTaskInvestment record);

    TimerTaskInvestment selectByPrimaryKey(Long id);
}
