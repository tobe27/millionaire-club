package com.millionaire.millionairemanagerservice.module;

public class Bank {
    private Long id;

    private String bankName;

    private Double singleLimit;

    private Double dailyLimit;

    private String paymentNumber;

    private String presentNumber;

    private String bankLogo;

    private Long gmtCreate;

    private Long gmtUpdate;

    private String founder;

    private String modifier;

    public Bank(Long id, String bankName, Double singleLimit, Double dailyLimit, String paymentNumber, String presentNumber, String bankLogo, Long gmtCreate, Long gmtUpdate, String founder, String modifier) {
        this.id = id;
        this.bankName = bankName;
        this.singleLimit = singleLimit;
        this.dailyLimit = dailyLimit;
        this.paymentNumber = paymentNumber;
        this.presentNumber = presentNumber;
        this.bankLogo = bankLogo;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.founder = founder;
        this.modifier = modifier;
    }

    public Long getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public Double getSingleLimit() {
        return singleLimit;
    }

    public Double getDailyLimit() {
        return dailyLimit;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public String getPresentNumber() {
        return presentNumber;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public String getFounder() {
        return founder;
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", singleLimit=" + singleLimit +
                ", dailyLimit=" + dailyLimit +
                ", paymentNumber='" + paymentNumber + '\'' +
                ", presentNumber='" + presentNumber + '\'' +
                ", bankLogo='" + bankLogo + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                ", founder='" + founder + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }

    public Bank() {
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setSingleLimit(Double singleLimit) {
        this.singleLimit = singleLimit;
    }

    public void setDailyLimit(Double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public void setPresentNumber(String presentNumber) {
        this.presentNumber = presentNumber;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}