package com.millionaire.millionairequartzmanager.service.impl;

import com.millionaire.millionairequartzmanager.dao.TimerTaskInvestmentMapper;
import com.millionaire.millionairequartzmanager.module.TimerTaskInvestment;
import com.millionaire.millionairequartzmanager.service.TimerTaskInvestmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TimerTaskInvestmentServiceImpl implements TimerTaskInvestmentService {
    @Resource
    private TimerTaskInvestmentMapper timerTaskInvestmentMapper;

    @Override
    public Long insert(TimerTaskInvestment record) {
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        timerTaskInvestmentMapper.insert(record);
        return record.getId();
    }

}
