package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

@Data
public class ClaimMatch {
    private Long id;

    private Long claimId;

    private Long investmentUserId;

    private String creditContractNumber;

    private Integer status;

    private Long gmtCreate;

    private Long gmtUpdate;

    public ClaimMatch(Long id, Long claimId, Long investmentUserId, String creditContractNumber, Integer status, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.claimId = claimId;
        this.investmentUserId = investmentUserId;
        this.creditContractNumber = creditContractNumber;
        this.status = status;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public ClaimMatch() {
    }

    public Long getId() {
        return id;
    }

    public Long getClaimId() {
        return claimId;
    }

    public Long getInvestmentUserId() {
        return investmentUserId;
    }

    public String getCreditContractNumber() {
        return creditContractNumber;
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