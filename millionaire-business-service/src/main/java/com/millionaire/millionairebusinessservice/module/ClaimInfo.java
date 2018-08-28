package com.millionaire.millionairebusinessservice.module;

import org.mybatis.spring.annotation.MapperScan;

import javax.validation.constraints.*;

/**
 * @Description 参数校验添加人lk，目前仅在新增时启用
 **/
public class ClaimInfo {
    @Null
    private Long id;

//    债权代号（原型图为给定生成规则：待定义）
    @NotBlank
    private String claimCode;

    //    债权人姓名
    @NotBlank
    @Size(max = 6,min = 2)
    @Pattern(regexp = "^\\S*\\S$")
    private String creditorName;

    //    债权人手机号
    @NotBlank
    @Pattern(regexp = "1[0-9]{10}")
    private String creditorPhone;

    //    债权人身份证号码
    @NotBlank
    @Pattern(regexp = "^[1-9]\\d{5}(1[8-9]|2[0-1])\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$|^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$")
    private String creditorIdNumber;

    //    出借日期
    @NotNull

    private Long lendingDate;

    //    出借期限
    @NotNull
    @Min(0)
    private Integer lendingPeriod;

    // 到期日期（=lendingDate+lendingPeriod）
    //新增时封装
    private Long expirationDate;

    //    出借金额
    @NotNull
    @Min(0)
    private Integer lendingAmount;

    //    债权性质
    @NotBlank
    @Pattern(regexp = "^\\S*\\S$")
    private String property;

    //    出借利率
    @NotNull
    @Min(0)
    private Double interestRate;

    //    未匹配金额
    //  新增时封装
    private Integer unMatchAmount;

    //    匹配利率
    //    新增时封装
    private Double matchRate;

    //    备注
    private String remarks;

    //    状态（是否开始匹配债权，是否到期）
    // 新增时封装
    private Integer status;

    @Null
    // 新增时封装
    private Long gmtCreate;

    @Null
    // 新增时封装
    private Long gmtUpdate;

    public ClaimInfo(Long id, String claimCode, String creditorName, String creditorPhone, String creditorIdNumber, Long lendingDate, Integer lendingPeriod, Long expirationDate, Integer lendingAmount, String property, Double interestRate, Integer unMatchAmount, Double matchRate, String remarks, Integer status, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.claimCode = claimCode;
        this.creditorName = creditorName;
        this.creditorPhone = creditorPhone;
        this.creditorIdNumber = creditorIdNumber;
        this.lendingDate = lendingDate;
        this.lendingPeriod = lendingPeriod;
        this.expirationDate = expirationDate;
        this.lendingAmount = lendingAmount;
        this.property = property;
        this.interestRate = interestRate;
        this.unMatchAmount = unMatchAmount;
        this.matchRate = matchRate;
        this.remarks = remarks;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "ClaimInfo{" +
                "id=" + id +
                ", claimCode='" + claimCode + '\'' +
                ", creditorName='" + creditorName + '\'' +
                ", creditorPhone='" + creditorPhone + '\'' +
                ", creditorIdNumber='" + creditorIdNumber + '\'' +
                ", lendingDate=" + lendingDate +
                ", lendingPeriod=" + lendingPeriod +
                ", expirationDate=" + expirationDate +
                ", lendingAmount=" + lendingAmount +
                ", property='" + property + '\'' +
                ", interestRate=" + interestRate +
                ", unMatchAmount=" + unMatchAmount +
                ", matchRate=" + matchRate +
                ", remarks='" + remarks + '\'' +
                ", status=" + status +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public ClaimInfo() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClaimCode(String claimCode) {
        this.claimCode = claimCode;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public void setCreditorPhone(String creditorPhone) {
        this.creditorPhone = creditorPhone;
    }

    public void setCreditorIdNumber(String creditorIdNumber) {
        this.creditorIdNumber = creditorIdNumber;
    }

    public void setLendingDate(Long lendingDate) {
        this.lendingDate = lendingDate;
    }

    public void setLendingPeriod(Integer lendingPeriod) {
        this.lendingPeriod = lendingPeriod;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setLendingAmount(Integer lendingAmount) {
        this.lendingAmount = lendingAmount;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setUnMatchAmount(Integer unMatchAmount) {
        this.unMatchAmount = unMatchAmount;
    }

    public void setMatchRate(Double matchRate) {
        this.matchRate = matchRate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getClaimCode() {
        return claimCode;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public String getCreditorPhone() {
        return creditorPhone;
    }

    public String getCreditorIdNumber() {
        return creditorIdNumber;
    }

    public Long getLendingDate() {
        return lendingDate;
    }

    public Integer getLendingPeriod() {
        return lendingPeriod;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public Integer getLendingAmount() {
        return lendingAmount;
    }

    public String getProperty() {
        return property;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getUnMatchAmount() {
        return unMatchAmount;
    }

    public Double getMatchRate() {
        return matchRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}