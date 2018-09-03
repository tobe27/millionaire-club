package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.TimerTaskInvestmentMapper;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.TimerTaskInvestmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;


@Service
public class TimerTaskInvestmentServiceImpl implements TimerTaskInvestmentService {

    private Logger logger = LoggerFactory.getLogger(TradingFlowServiceImpl.class);
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
        List<TimerTaskInvestment> list = timerTaskInvestmentMapper.selectIdForRenewalInvestment(investmentUserId);
        logger.info("获得投资定时任务信息"+list);
        logger.info("==============================================================");
        Comparator<TimerTaskInvestment> comparator = Comparator.comparing(TimerTaskInvestment::getTimes);
        TimerTaskInvestment taskInvestment = list.stream().max(comparator).get();
        logger.info("筛选后的投资定时任务信息"+taskInvestment);
        logger.info("==============================================================");
        return taskInvestment ;
    }

    @Override
    public int updateTimerTaskForRenewal(int paybackAmount, byte executeType, long associationInvestment, long id) {
        return timerTaskInvestmentMapper.updateTimerTaskForRenewal(paybackAmount,executeType,associationInvestment,id);
    }


}
