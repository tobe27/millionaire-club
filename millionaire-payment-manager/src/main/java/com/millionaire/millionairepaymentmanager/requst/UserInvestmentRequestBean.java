package com.millionaire.millionairepaymentmanager.requst;

import lombok.Data;

@Data
public class UserInvestmentRequestBean {
//    投资产品主键
    private long productId;
//    用户银行主键
    private long UserBankId;
//    投资金额
    private int amount;
//    合同签名图片
    private String contractSign;
}
