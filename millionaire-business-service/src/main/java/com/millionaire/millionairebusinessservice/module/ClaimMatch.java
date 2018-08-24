package com.millionaire.millionairebusinessservice.module;

public class ClaimMatch {
    private Long id;

//    债权id
    private Long claimId;

//    用户投资id
    private Long investmentUserId;

//    债权编号，每一份匹配债权的合同编号不一样
    private String creditContractNumber;

    private Long gmtCreate;

    private Long gmtUpdate;

    public ClaimMatch(Long id, Long claimId, Long investmentUserId, String creditContractNumber, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.claimId = claimId;
        this.investmentUserId = investmentUserId;
        this.creditContractNumber = creditContractNumber;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "ClaimMatch{" +
                "id=" + id +
                ", claimId=" + claimId +
                ", investmentUserId=" + investmentUserId +
                ", creditContractNumber='" + creditContractNumber + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }

    public ClaimMatch() {
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public void setInvestmentUserId(Long investmentUserId) {
        this.investmentUserId = investmentUserId;
    }

    public void setCreditContractNumber(String creditContractNumber) {
        this.creditContractNumber = creditContractNumber;
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

    public Long getClaimId() {
        return claimId;
    }

    public Long getInvestmentUserId() {
        return investmentUserId;
    }

    public String getCreditContractNumber() {
        return creditContractNumber;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }
}