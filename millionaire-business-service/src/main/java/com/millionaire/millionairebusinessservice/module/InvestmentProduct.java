package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

import javax.validation.constraints.*;
@Data
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
    @Min(0)
    private Integer startingAmount;

    //    起息日期
    @NotNull
    private Byte valueDate;

    //    期限
    @NotNull
    @Min(0)
    private Integer deadline;

    //    备注
    private String remark;

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

    public InvestmentProduct(@Null Long id, @NotBlank @Pattern(regexp = "^\\S*\\S$") String productCode, @NotBlank @Pattern(regexp = "^\\S*\\S$") String name, @NotNull @Min(0) Double annualizedIncome, @NotNull Byte repaymentMode, @NotNull @Min(0) Integer startingAmount, @NotNull Byte valueDate, @NotNull @Min(0) Integer deadline, String remark, @NotBlank @Pattern(regexp = "^\\S*\\S$") String moreDetails, @NotNull Byte type, @NotNull Byte isRecommend, @NotNull Byte isPurchaseLimit, @NotNull Byte isShelf, @Null Long gmtCreate, @Null Long gmtUpdate) {
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

    public InvestmentProduct() {
    }

    public Long getId() {
        return id;
    }

    public InvestmentProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductCode() {
        return productCode;
    }

    public InvestmentProduct setProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public InvestmentProduct setName(String name) {
        this.name = name;
        return this;
    }

    public Double getAnnualizedIncome() {
        return annualizedIncome;
    }

    public InvestmentProduct setAnnualizedIncome(Double annualizedIncome) {
        this.annualizedIncome = annualizedIncome;
        return this;
    }

    public Byte getRepaymentMode() {
        return repaymentMode;
    }

    public InvestmentProduct setRepaymentMode(Byte repaymentMode) {
        this.repaymentMode = repaymentMode;
        return this;
    }

    public Integer getStartingAmount() {
        return startingAmount;
    }

    public InvestmentProduct setStartingAmount(Integer startingAmount) {
        this.startingAmount = startingAmount;
        return this;
    }

    public Byte getValueDate() {
        return valueDate;
    }

    public InvestmentProduct setValueDate(Byte valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public InvestmentProduct setDeadline(Integer deadline) {
        this.deadline = deadline;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public InvestmentProduct setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getMoreDetails() {
        return moreDetails;
    }

    public InvestmentProduct setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
        return this;
    }

    public Byte getType() {
        return type;
    }

    public InvestmentProduct setType(Byte type) {
        this.type = type;
        return this;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public InvestmentProduct setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
        return this;
    }

    public Byte getIsPurchaseLimit() {
        return isPurchaseLimit;
    }

    public InvestmentProduct setIsPurchaseLimit(Byte isPurchaseLimit) {
        this.isPurchaseLimit = isPurchaseLimit;
        return this;
    }

    public Byte getIsShelf() {
        return isShelf;
    }

    public InvestmentProduct setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
        return this;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public InvestmentProduct setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public InvestmentProduct setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
        return this;
    }
}