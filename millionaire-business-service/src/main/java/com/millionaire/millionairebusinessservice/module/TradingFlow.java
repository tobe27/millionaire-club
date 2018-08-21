package com.millionaire.millionairebusinessservice.module;

public class TradingFlow {
    private Long id;

    private String productName;

    private String phone;

    private String name;

    private Integer amount;

    private Byte type;

    private String bankCardId;

    private String payType;

    private Byte status;

    private Long gmtCreate;

    private Long gmtUpdate;

    public TradingFlow(Long id, String productName, String phone, String name, Integer amount, Byte type, String bankCardId, String payType, Byte status, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TradingFlow{" +
                "id=" + id +
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