package com.millionaire.millionairebusinessservice.request;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 查询用户投资记录参数包装类
 * @date 2018/8/24 9:20
 */
@Data
public class InvestmentUserQuery {
    // 用户id
    private Long uid;

    //    产品编号
    private String productName;

    //    起息时间上下限
    private Long valueStartlowerDate;
    private Long valueStartupperDate;

    //    到息时间上下限
    private Long valueEndlowerDate;
    private Long valueEndupperDate;

    //    产品协议编号
    private String lendingContractNumber;

    //    债权编号，每一份匹配债权的合同编号不一样
    private String creditContractNumber;

    //    投资状态
    private Byte investmentStatus;
}
