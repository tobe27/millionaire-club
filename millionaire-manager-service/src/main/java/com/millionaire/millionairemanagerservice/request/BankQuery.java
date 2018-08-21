package com.millionaire.millionairemanagerservice.request;

/**
 * @author Liu Kai
 * @Description: TODO 用于接收bank查询的相关参数
 * @date 2018/8/21 16:02
 */

public class BankQuery {

    private String bankName;
    private String modifier;
    private Long lowerDate;
    private Long upperDate;
    private Double upperSingleLimit;
    private Double lowerSingleLimit;
    private Double upperDailyLimit;
    private Double lowerDailyLimit;


    public BankQuery() {
    }



    public Double getUpperSingleLimit() {
        return upperSingleLimit;
    }

    public BankQuery setUpperSingleLimit(Double upperSingleLimit) {
        this.upperSingleLimit = upperSingleLimit;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public BankQuery setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getModifier() {
        return modifier;
    }

    public BankQuery setModifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public BankQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public BankQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }

    public Double getLowerSingleLimit() {
        return lowerSingleLimit;
    }

    public BankQuery setLowerSingleLimit(Double lowerSingleLimit) {
        this.lowerSingleLimit = lowerSingleLimit;
        return this;
    }

    public Double getUpperDailyLimit() {
        return upperDailyLimit;
    }

    public BankQuery setUpperDailyLimit(Double upperDailyLimit) {
        this.upperDailyLimit = upperDailyLimit;
        return this;
    }

    public Double getLowerDailyLimit() {
        return lowerDailyLimit;
    }

    public BankQuery setLowerDailyLimit(Double lowerDailyLimit) {
        this.lowerDailyLimit = lowerDailyLimit;
        return this;
    }
}
