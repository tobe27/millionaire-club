package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

@Data
public class TradingFlow {
    private Long id;

    private Long userInvestmentId;

    private Long productName;

    private Long uid;

    private Integer amount;

    private Byte type;

    private String bankCardId;

    private Byte status;

    private Long gmtCreate;

    private Long gmtUpdate;

    public TradingFlow() {
    }

    public TradingFlow(Long id, Long userInvestmentId, Long productName, Long uid, Integer amount, Byte type, String bankCardId, Byte status, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.userInvestmentId = userInvestmentId;
        this.productName = productName;
        this.uid = uid;
        this.amount = amount;
        this.type = type;
        this.bankCardId = bankCardId;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserInvestmentId() {
        return userInvestmentId;
    }

    public Long getProductName() {
        return productName;
    }

    public Long getUid() {
        return uid;
    }

    public Integer getAmount() {
        return amount;
    }

    public Byte getType() {
        return type;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public Byte getStatus() {
        return status;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}