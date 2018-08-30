package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

@Data
public class TimerTaskInvestment {
    private Long id;
    private Long investmentUserId;
//    汇款金额
    private Integer paybackAmount;
//    10代表本息一次回款20代表分期还息30代表最后一次还息回款
    private Byte executeType;
//    针对第几次还息 本息一次回款默认1
    private Byte times;
//    任务执行状态： 0 待执行 10执行成功 20取消 30失败
    private Byte status;
//    任务执行时间
    private Long executeTime;
    private Long gmtCreate;
    private Long gmtUpdate;

    public TimerTaskInvestment(Long id, Long investmentUserId, Integer paybackAmount, Byte executeType, Byte times, Byte status, Long executeTime, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.investmentUserId = investmentUserId;
        this.paybackAmount = paybackAmount;
        this.executeType = executeType;
        this.times = times;
        this.status = status;
        this.executeTime = executeTime;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public TimerTaskInvestment() {
    }
}
