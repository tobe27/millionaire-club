package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.TimerTaskInvestmentMapper;
import com.millionaire.millionairebusinessservice.exception.TimerTaskException;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.TimerTaskInvestmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<TimerTaskInvestment> listTimerTaskForExecute() {
        return timerTaskInvestmentMapper.listTimerTaskForExecute();
    }

    @Override
    public TimerTaskInvestment selectIdForRenewalInvestment(Long investmentUserId) throws Exception {
        return timerTaskInvestmentMapper.selectIdForRenewalInvestment(investmentUserId);
    }

    @Override
    public int updateTimerTaskForRenewal(int paybackAmount, byte executeType, long associationInvestment, long id) {
        return timerTaskInvestmentMapper.updateTimerTaskForRenewal(paybackAmount,executeType,associationInvestment,id);
    }


}
