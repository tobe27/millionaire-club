package com.millionaire.millionairebusinessservice.module;

import com.millionaire.millionairebusinessservice.validatedgroup.InsertClaimInfoGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * insertClaimInfoGroup 添加债券参数分组校验
 **/

@Data
public class ClaimInfo {

    private Long id;

    @NotBlank(groups = InsertClaimInfoGroup.class)
    private String claimCode;

    private String claimContract;

    @NotBlank(groups = InsertClaimInfoGroup.class)
    private String creditorName;

    @NotBlank(groups = InsertClaimInfoGroup.class)
    private String creditorPhone;

    @NotBlank(groups = InsertClaimInfoGroup.class)
    private String creditorIdNumber;

//    @NotNull(groups = InsertClaimInfoGroup.class)
    private String lendingDate;

    @NotNull(groups = InsertClaimInfoGroup.class)
    private Integer lendingPeriod;

    private Date expirationDate;

    @NotNull(groups = InsertClaimInfoGroup.class)
    private Integer lendingAmount;

    @NotBlank(groups = InsertClaimInfoGroup.class)
    private String property;

    @NotNull(groups = InsertClaimInfoGroup.class)
    private Double interestRate;

    private Integer matchAmount;

//    private Double matchrate;
//
//    private Double unmatchamount;

    @NotBlank(groups = InsertClaimInfoGroup.class)
    private String remarks;

    private Integer status;

    @Null(groups = InsertClaimInfoGroup.class)
    private Long gmtCreate;

    @Null(groups = InsertClaimInfoGroup.class)
    private Long gmtUpdate;

    public ClaimInfo() {
    }

    public ClaimInfo(Long id, String claimCode, String claimContract, String creditorName, String creditorPhone, String creditorIdNumber, String lendingDate, Integer lendingPeriod, Date expirationDate, Integer lendingAmount, String property, Double interestRate, Integer matchAmount, String remarks, Integer status, Long gmtCreate, Long gmtUpdate) {
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
//        this.matchrate = matchrate;
//        this.unmatchamount = unmatchamount;
        this.remarks = remarks;
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

    public String getLendingDate() {
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

    public Integer getMatchAmount() {
        return matchAmount;
    }

//    public Double getMatchrate() {
//        return matchrate;
//    }
//
//    public Double getUnmatchamount() {
//        return unmatchamount;
//    }

    public String getremarks() {
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