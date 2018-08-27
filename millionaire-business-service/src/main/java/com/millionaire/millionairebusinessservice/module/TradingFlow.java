package com.millionaire.millionairebusinessservice.module;

public class TradingFlow {
    private Long id;

    private Long uid;

    private Long investmentUserId;

//    产品名称
    private String productName;

//    用户手机号
    private String phone;

//    用户姓名
    private String name;

//    投资金额
    private Integer amount;

//    （-1付款，1回款）
    private Byte type;

//    银行卡账号
    private String bankCardId;

//    交易方式（哪家银行）
    private String payType;

//    交易状态
    private Byte status;

    private Long gmtCreate;

    private Long gmtUpdate;

    public TradingFlow(Long id, Long uid, String productName, String phone, String name, Integer amount, Byte type, String bankCardId, String payType, Byte status, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.uid = uid;
        this.productName = productName;
        this.phone = phone;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.bankCardId = bankCardId;
        this.payType = payType;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getInvestmentUserId() {
        return investmentUserId;
    }

    public void setInvestmentUserId(Long investmentUserId) {
        this.investmentUserId = investmentUserId;
    }

    @Override
    public String toString() {
        return "TradingFlow{" +
                "id=" + id +
                ", uid=" + uid +
                ", investmentUserId=" + investmentUserId +
                ", productName='" + productName + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", bankCardId='" + bankCardId + '\'' +
                ", payType='" + payType + '\'' +
                ", status=" + status +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public TradingFlow setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public TradingFlow() {

    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
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

    public String getPayType() {
        return payType;
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