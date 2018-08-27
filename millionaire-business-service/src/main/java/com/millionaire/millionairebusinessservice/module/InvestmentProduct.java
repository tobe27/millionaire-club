package com.millionaire.millionairebusinessservice.module;

import javax.validation.constraints.*;

public class InvestmentProduct {

    @Null
    private Long id;

    // 产品代号
    @NotBlank
    @Pattern(regexp = "^\\S*\\S$")
    private String productCode;

    // 产品名称
    @NotBlank
    @Pattern(regexp = "^\\S*\\S$")
    private String name;

    // 产品收益率
    @NotNull
    @Min(0)
    private Double annualizedIncome;

    //    还款方式
    @NotNull
    private Byte repaymentMode;

    //    起投金额
    @NotNull
    private Integer startingAmount;

    //    起息日期
    @NotNull
    private Byte valueDate;

    //    期限
    @NotNull
    private Integer deadline;

    //    备注
    private String describe;

    //    产品详情
    @NotBlank
    @Pattern(regexp = "^\\S*\\S$")
    private String moreDetails;

    //    类型
    @NotNull
    private Byte type;

    //    是否推荐
    @NotNull
    private Byte isRecommend;

    //    是否限购
    @NotNull
    private Byte isPurchaseLimit;

    //    上下架
    @NotNull
    private Byte isShelf;
    @Null
    private Long gmtCreate;
    @Null
    private Long gmtUpdate;

    public InvestmentProduct(Long id, String productCode, String name, Double annualizedIncome, Byte repaymentMode, Integer startingAmount, Byte valueDate, Integer deadline, String describe, String moreDetails, Byte type, Byte isRecommend, Byte isPurchaseLimit, Byte isShelf, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.annualizedIncome = annualizedIncome;
        this.repaymentMode = repaymentMode;
        this.startingAmount = startingAmount;
        this.valueDate = valueDate;
        this.deadline = deadline;
        this.describe = describe;
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
                ", describe='" + describe + '\'' +
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

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public String getDescribe() {
        return describe;
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