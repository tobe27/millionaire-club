package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

import java.util.Date;

@Data
public class ClaimInfo {
    private Long id;

    private String claimCode;

    private String claimContract;

    private String creditorName;

    private String creditorPhone;

    private String creditorIdNumber;

    private Date lendingDate;

    private Integer lendingPeriod;

    private Date expirationDate;

    private Integer lendingAmount;

    private String property;

    private Double interestRate;

    private Double matchAmount;

    private Double matchrate;

    private Double unmatchamount;

    private String describe;

    private Integer status;

    private Long gmtCreate;

    private Long gmtUpdate;

    public ClaimInfo() {
    }

    public ClaimInfo(Long id, String claimCode, String claimContract, String creditorName, String creditorPhone, String creditorIdNumber, Date lendingDate, Integer lendingPeriod, Date expirationDate, Integer lendingAmount, String property, Double interestRate, Double matchAmount, Double matchrate, Double unmatchamount, String describe, Integer status, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.claimCode = claimCode;
        this.claimContract = claimContract;
        this.creditorName = creditorName;
        this.creditorPhone = creditorPhone;
        this.creditorIdNumber = creditorIdNumber;
        this.lendingDate = lendingDate;
        this.lendingPeriod = lendingPeriod;
        this.expirationDate = expirationDate;
        this.lendingAmount = lendingAmount;
        this.property = property;
        this.interestRate = interestRate;
        this.matchAmount = matchAmount;
        this.matchrate = matchrate;
        this.unmatchamount = unmatchamount;
        this.describe = describe;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public Long getId() {
        return id;
    }

    public String getClaimCode() {
        return claimCode;
    }

    public String getClaimContract() {
        return claimContract;
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

    public Date getLendingDate() {
        return lendingDate;
    }

    public Integer getLendingPeriod() {
        return lendingPeriod;
    }

    public Date getExpirationDate() {
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

    public Double getMatchAmount() {
        return matchAmount;
    }

    public Double getMatchrate() {
        return matchrate;
    }

    public Double getUnmatchamount() {
        return unmatchamount;
    }

    public String getDescribe() {
        return describe;
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