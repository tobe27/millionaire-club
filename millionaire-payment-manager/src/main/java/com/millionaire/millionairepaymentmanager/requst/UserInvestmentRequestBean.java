package com.millionaire.millionairepaymentmanager.requst;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserInvestmentRequestBean {
//    投资产品主键
    private long productId;
//    用户银行主键
    private long userBankId;

    /**
     * TODO bug
     */
//    投资金额
    @Pattern(regexp="^[0-9]*0{4,}$",message = "金额必须为10000的整数倍")
    private int amount;
//    合同签名图片
    private String contractSign;
}
