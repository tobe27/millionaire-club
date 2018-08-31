package com.millionaire.millionairebusinessservice.transport;

import lombok.Data;

@Data
public class RenewalInvestmentDTO {
    private Long id;
    private String name;     //产品名称
    private Double annualizedIncome;      //年化收益率
    private Integer investmentAmount;    //投资金额
    private Integer deadline;//理财天数
    private Byte type; //角标分类
    private Integer startingAmount;  //起投金额
    private Long valueDateStart;    //起息时间
    private Long valueDateEnd;  //到期时间
    //    预期收益
    private Double expectedIncome;
//    投资状态
    private Byte investmentStatus;
//    用户投资的银行名称
    private String bankName;
//    用户投资的账户号
    private String bankCardNumber;
    //    已分配收益
    private Double distributedIncome;
}
