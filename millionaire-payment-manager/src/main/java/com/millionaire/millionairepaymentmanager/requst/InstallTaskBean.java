package com.millionaire.millionairepaymentmanager.requst;

import lombok.Data;

@Data
public class InstallTaskBean {
    //    汇款金额
    private Double paybackAmount;
    //    针对第几次还息 本息一次回款默认1
    private Byte times;
    //    任务执行时间
    private Long executeTime;
}
