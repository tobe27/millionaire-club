package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.exception.TimerTaskException;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimerTaskInvestmentService {
    Long insert(TimerTaskInvestment record);

    /**
     * 获取即将执行的投资定时任务
     */
    List<TimerTaskInvestment> listTimerTaskForExecute();

    /**
     * 查询即将续投投资的任务
     */
    TimerTaskInvestment selectIdForRenewalInvestment(Long investmentUserId) throws Exception;


    /**
     * 用户投资续投后的，定时任务更新
     * @return
     */
    int updateTimerTaskForRenewal(@Param("paybackAmount") int paybackAmount,
                                  @Param("executeType") byte executeType,
                                  @Param("associationInvestment") long associationInvestment,
                                  @Param("id")long id);


}
