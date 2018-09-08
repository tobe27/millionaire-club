package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TimerTaskInvestmentMapper {

    int insert(TimerTaskInvestment record);

    TimerTaskInvestment selectByPrimaryKey(Long id);

    /**
     * 获取即将执行的投资定时任务
     */
    List<TimerTaskInvestment> listTimerTaskForExecute();

    /**
     * 查询即将续投投资的任务
     */
    List<TimerTaskInvestment> selectIdForRenewalInvestment(Long investmentUserId);

    /**
     * 用户投资续投后的，定时任务更新
     * @return
     */
    int updateTimerTaskForRenewal(@Param("paybackAmount") int paybackAmount,
                                  @Param("executeType") byte executeType,
                                  @Param("associationInvestment") long associationInvestment,
                                  @Param("id")long id,
                                  @Param("gmtUpdate")long gmtUpdate);

    /**
     * @author qiaobao
     * @datetime 2018/9/4 22:11
     * @decribe
     */
    int updateTaskStatus(@Param("status") Byte status,
                         @Param("id") long id,
                         @Param("gmtUpdate") long gmtUpdate);


}
