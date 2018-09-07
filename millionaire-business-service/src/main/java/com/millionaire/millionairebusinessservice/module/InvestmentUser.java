package com.millionaire.millionairebusinessservice.module;

public class InvestmentUser {
    private Long id;

//    产品编号
    private Long productId;

//    用户id
    private Long uid;

//     匹配的债权id
    private Long claimId;

//    起息时间
    private Long valueDateStart;

//    到息时间
    private Long valueDateEnd;

//    产品协议编号
    private String lendingContractNumber;

    //    用户合同签名
    private String contractSign;

//    投资金额
    private Integer investmentAmount;

//    投资状态
    private Byte investmentStatus;

//    预期收益
    private Double expectedIncome;

//    已分配收益
    private Double distributedIncome;

    private Long gmtCreate;

    private Long gmtUpdate;

    private String bankCardNumber;

    private String bankName;

    private Byte look;

    public InvestmentUser() {
    }

    public InvestmentUser(Long id, Long productId, Long uid, Long claimId, Long valueDateStart, Long valueDateEnd, String lendingContractNumber, String contractSign, Integer investmentAmount, Byte investmentStatus, Double expectedIncome, Double distributedIncome, Long gmtCreate, Long gmtUpdate, String bankCardNumber, String bankName, Byte look) {
        this.id = id;
        this.productId = productId;
        this.uid = uid;
        this.claimId = claimId;
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
        this.bankCardNumber = bankCardNumber;
        this.bankName = bankName;
        this.look = look;
    }

    @Override
    public String toString() {
        return "InvestmentUser{" +
                "id=" + id +
                ", productId=" + productId +
                ", uid=" + uid +
                ", claimId=" + claimId +
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
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", look=" + look +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Long getValueDateStart() {
        return valueDateStart;
    }

    public void setValueDateStart(Long valueDateStart) {
        this.valueDateStart = valueDateStart;
    }

    public Long getValueDateEnd() {
        return valueDateEnd;
    }

    public void setValueDateEnd(Long valueDateEnd) {
        this.valueDateEnd = valueDateEnd;
    }

    public String getLendingContractNumber() {
        return lendingContractNumber;
    }

    public void setLendingContractNumber(String lendingContractNumber) {
        this.lendingContractNumber = lendingContractNumber;
    }

    public String getContractSign() {
        return contractSign;
    }

    public void setContractSign(String contractSign) {
        this.contractSign = contractSign;
    }

    public Integer getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Integer investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public Byte getInvestmentStatus() {
        return investmentStatus;
    }

    public void setInvestmentStatus(Byte investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getDistributedIncome() {
        return distributedIncome;
    }

    public void setDistributedIncome(Double distributedIncome) {
        this.distributedIncome = distributedIncome;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Byte getLook() {
        return look;
    }

    public void setLook(Byte look) {
        this.look = look;
    }
}