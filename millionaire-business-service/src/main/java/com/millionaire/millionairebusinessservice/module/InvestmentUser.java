package com.millionaire.millionairebusinessservice.module;

import lombok.Data;

import java.util.Date;

@Data
public class InvestmentUser {
    private Long id;

    private Long productId;

    private Long uid;

    private Date valueDateStart;

    private Date valueDateEnd;

    private String lendingContractNumber;

    private String contractSign;

    private Integer investmentAmount;

    private Byte investmentStatus;

    private Byte projectStatus;

    private Double undistributedIncome;

    private Double distributedIncome;

    private Date translationDate;

    private Long gmtCreate;

    private Long gmtUpdate;

    private String claimId;

    public InvestmentUser() {
    }

    public InvestmentUser(Long id, Long productId, Long uid, Date valueDateStart, Date valueDateEnd, String lendingContractNumber, String contractSign, Integer investmentAmount, Byte investmentStatus, Byte projectStatus, Double undistributedIncome, Double distributedIncome, Date translationDate, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.productId = productId;
        this.uid = uid;
        this.valueDateStart = valueDateStart;
        this.valueDateEnd = valueDateEnd;
        this.lendingContractNumber = lendingContractNumber;
        this.contractSign = contractSign;
        this.investmentAmount = investmentAmount;
        this.investmentStatus = investmentStatus;
        this.projectStatus = projectStatus;
        this.undistributedIncome = undistributedIncome;
        this.distributedIncome = distributedIncome;
        this.translationDate = translationDate;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public InvestmentUser(Long id, Long productId, Long uid, Date valueDateStart, Date valueDateEnd, String lendingContractNumber, String contractSign, Integer investmentAmount, Byte investmentStatus, Byte projectStatus, Double undistributedIncome, Double distributedIncome, Date translationDate, Long gmtCreate, Long gmtUpdate, String claimId) {
        this.id = id;
        this.productId = productId;
        this.uid = uid;
        this.valueDateStart = valueDateStart;
        this.valueDateEnd = valueDateEnd;
        this.lendingContractNumber = lendingContractNumber;
        this.contractSign = contractSign;
        this.investmentAmount = investmentAmount;
        this.investmentStatus = investmentStatus;
        this.projectStatus = projectStatus;
        this.undistributedIncome = undistributedIncome;
        this.distributedIncome = distributedIncome;
        this.translationDate = translationDate;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.claimId = claimId;
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

    public Date getValueDateStart() {
        return valueDateStart;
    }

    public Date getValueDateEnd() {
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

    public Byte getProjectStatus() {
        return projectStatus;
    }

    public Double getUndistributedIncome() {
        return undistributedIncome;
    }

    public Double getDistributedIncome() {
        return distributedIncome;
    }

    public Date getTranslationDate() {
        return translationDate;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public String getClaimId() {
        return claimId;
    }
}