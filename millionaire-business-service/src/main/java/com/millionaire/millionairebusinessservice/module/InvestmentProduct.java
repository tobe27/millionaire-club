package com.millionaire.millionairebusinessservice.module;

public class InvestmentProduct {
    private Long id;

    private String productCode;

    private String name;

    private Double annualizedIncome;

    private Byte repaymentMode;

    private Integer startingAmount;

    private Byte valueDate;

    private Integer deadline;

    private String remark;

    private String moreDetails;

    private Byte type;

    private Byte isRecommend;

    private Byte isPurchaseLimit;

    private Byte isShelf;

    private Long gmtCreate;

    private Long gmtUpdate;

    public InvestmentProduct(Long id, String productCode, String name, Double annualizedIncome, Byte repaymentMode, Integer startingAmount, Byte valueDate, Integer deadline, String remark, String moreDetails, Byte type, Byte isRecommend, Byte isPurchaseLimit, Byte isShelf, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.annualizedIncome = annualizedIncome;
        this.repaymentMode = repaymentMode;
        this.startingAmount = startingAmount;
        this.valueDate = valueDate;
        this.deadline = deadline;
        this.remark = remark;
        this.moreDetails = moreDetails;
        this.type = type;
        this.isRecommend = isRecommend;
        this.isPurchaseLimit = isPurchaseLimit;
        this.isShelf = isShelf;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "InvestmentProduct{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", annualizedIncome=" + annualizedIncome +
                ", repaymentMode=" + repaymentMode +
                ", startingAmount=" + startingAmount +
                ", valueDate=" + valueDate +
                ", deadline=" + deadline +
                ", remark='" + remark + '\'' +
                ", moreDetails='" + moreDetails + '\'' +
                ", type=" + type +
                ", isRecommend=" + isRecommend +
                ", isPurchaseLimit=" + isPurchaseLimit +
                ", isShelf=" + isShelf +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public InvestmentProduct() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnnualizedIncome(Double annualizedIncome) {
        this.annualizedIncome = annualizedIncome;
    }

    public void setRepaymentMode(Byte repaymentMode) {
        this.repaymentMode = repaymentMode;
    }

    public void setStartingAmount(Integer startingAmount) {
        this.startingAmount = startingAmount;
    }

    public void setValueDate(Byte valueDate) {
        this.valueDate = valueDate;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public void setIsPurchaseLimit(Byte isPurchaseLimit) {
        this.isPurchaseLimit = isPurchaseLimit;
    }

    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public Double getAnnualizedIncome() {
        return annualizedIncome;
    }

    public Byte getRepaymentMode() {
        return repaymentMode;
    }

    public Integer getStartingAmount() {
        return startingAmount;
    }

    public Byte getValueDate() {
        return valueDate;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public String getRemark() {
        return remark;
    }

    public String getMoreDetails() {
        return moreDetails;
    }

    public Byte getType() {
        return type;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public Byte getIsPurchaseLimit() {
        return isPurchaseLimit;
    }

    public Byte getIsShelf() {
        return isShelf;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}