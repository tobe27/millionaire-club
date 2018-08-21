package com.millionaire.millionairebusinessservice.module;

public class InvestmentUser {
    private Long id;

    private Long productId;

    private Long uid;

    private Long valueDateStart;

    private Long valueDateEnd;

    private String lendingContractNumber;

    private String contractSign;

    private Integer investmentAmount;

    private Byte investmentStatus;

    private Double expectedIncome;

    private Double distributedIncome;

    private Long gmtCreate;

    private Long gmtUpdate;

    public InvestmentUser(Long id, Long productId, Long uid, Long valueDateStart, Long valueDateEnd, String lendingContractNumber, String contractSign, Integer investmentAmount, Byte investmentStatus, Double expectedIncome, Double distributedIncome, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.productId = productId;
        this.uid = uid;
        this.valueDateStart = valueDateStart;
        this.valueDateEnd = valueDateEnd;
        this.lendingContractNumber = lendingContractNumber;
        this.contractSign = contractSign;
        this.investmentAmount = investmentAmount;
        this.investmentStatus = investmentStatus;
        this.expectedIncome = expectedIncome;
        this.distributedIncome = distributedIncome;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "InvestmentUser{" +
                "id=" + id +
                ", productId=" + productId +
                ", uid=" + uid +
                ", valueDateStart=" + valueDateStart +
                ", valueDateEnd=" + valueDateEnd +
                ", lendingContractNumber='" + lendingContractNumber + '\'' +
                ", contractSign='" + contractSign + '\'' +
                ", investmentAmount=" + investmentAmount +
                ", investmentStatus=" + investmentStatus +
                ", expectedIncome=" + expectedIncome +
                ", distributedIncome=" + distributedIncome +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setValueDateStart(Long valueDateStart) {
        this.valueDateStart = valueDateStart;
    }

    public void setValueDateEnd(Long valueDateEnd) {
        this.valueDateEnd = valueDateEnd;
    }

    public void setLendingContractNumber(String lendingContractNumber) {
        this.lendingContractNumber = lendingContractNumber;
    }

    public void setContractSign(String contractSign) {
        this.contractSign = contractSign;
    }

    public void setInvestmentAmount(Integer investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public void setInvestmentStatus(Byte investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public void setDistributedIncome(Double distributedIncome) {
        this.distributedIncome = distributedIncome;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public InvestmentUser() {

    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUid() {
        return uid;
    }

    public Long getValueDateStart() {
        return valueDateStart;
    }

    public Long getValueDateEnd() {
        return valueDateEnd;
    }

    public String getLendingContractNumber() {
        return lendingContractNumber;
    }

    public String getContractSign() {
        return contractSign;
    }

    public Integer getInvestmentAmount() {
        return investmentAmount;
    }

    public Byte getInvestmentStatus() {
        return investmentStatus;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public Double getDistributedIncome() {
        return distributedIncome;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}