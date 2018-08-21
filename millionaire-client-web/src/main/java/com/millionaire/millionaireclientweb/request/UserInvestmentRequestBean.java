package com.millionaire.millionaireclientweb.request;

import lombok.Data;

@Data
public class UserInvestmentRequestBean {
    private long productId;
    private long userBankId;
    private int amount;
    private String contractSign;
}
