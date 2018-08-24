package com.millionaire.millionairebusinessservice.request;

import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @author Liu Kai
 * @Description: TODO 接收新增债券信息参数类
 * @date 2018/8/21 10:03
 */

public class ClaimInfoQuery {

    //    债权代号（原型图为给定生成规则：待定义）
    private String claimCode;

    //    债权姓名
    private String creditorName;

    //    债权人手机号
    private String creditorPhone;

    //    债权人身份证号码
    private String creditorIdNumber;

    //    状态（是否开始匹配债权，是否到期）
    private Integer status;

    //    出借日期上下限
    private Long lowerLendingDate;
    private Long upperLendingDate;
    //    出借期限上下限
    private Integer lowerLendingPeriod;
    private Integer upperLendingPeriod;

    //    到期日期上下限
    private Long lowerExpirationDate;
    private Long upperExpirationDate;

    //    出借金额上下限
    private Integer lowerLendingAmount;
    private Integer upperLendingAmount;


    public ClaimInfoQuery() {
    }

    public String getClaimCode() {
        return claimCode;
    }

    public ClaimInfoQuery setClaimCode(String claimCode) {
        this.claimCode = claimCode;
        return this;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public ClaimInfoQuery setCreditorName(String creditorName) {
        this.creditorName = creditorName;
        return this;
    }

    public String getCreditorPhone() {
        return creditorPhone;
    }

    public ClaimInfoQuery setCreditorPhone(String creditorPhone) {
        this.creditorPhone = creditorPhone;
        return this;
    }

    public String getCreditorIdNumber() {
        return creditorIdNumber;
    }

    public ClaimInfoQuery setCreditorIdNumber(String creditorIdNumber) {
        this.creditorIdNumber = creditorIdNumber;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ClaimInfoQuery setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getLowerLendingDate() {
        return lowerLendingDate;
    }

    public ClaimInfoQuery setLowerLendingDate(Long lowerLendingDate) {
        this.lowerLendingDate = lowerLendingDate;
        return this;
    }

    public Long getUpperLendingDate() {
        return upperLendingDate;
    }

    public ClaimInfoQuery setUpperLendingDate(Long upperLendingDate) {
        this.upperLendingDate = upperLendingDate;
        return this;
    }

    public Integer getLowerLendingPeriod() {
        return lowerLendingPeriod;
    }

    public ClaimInfoQuery setLowerLendingPeriod(Integer lowerLendingPeriod) {
        this.lowerLendingPeriod = lowerLendingPeriod;
        return this;
    }

    public Integer getUpperLendingPeriod() {
        return upperLendingPeriod;
    }

    public ClaimInfoQuery setUpperLendingPeriod(Integer upperLendingPeriod) {
        this.upperLendingPeriod = upperLendingPeriod;
        return this;
    }

    public Long getLowerExpirationDate() {
        return lowerExpirationDate;
    }

    public ClaimInfoQuery setLowerExpirationDate(Long lowerExpirationDate) {
        this.lowerExpirationDate = lowerExpirationDate;
        return this;
    }

    public Long getUpperExpirationDate() {
        return upperExpirationDate;
    }

    public ClaimInfoQuery setUpperExpirationDate(Long upperExpirationDate) {
        this.upperExpirationDate = upperExpirationDate;
        return this;
    }

    public Integer getLowerLendingAmount() {
        return lowerLendingAmount;
    }

    public ClaimInfoQuery setLowerLendingAmount(Integer lowerLendingAmount) {
        this.lowerLendingAmount = lowerLendingAmount;
        return this;
    }

    public Integer getUpperLendingAmount() {
        return upperLendingAmount;
    }

    public ClaimInfoQuery setUpperLendingAmount(Integer upperLendingAmount) {
        this.upperLendingAmount = upperLendingAmount;
        return this;
    }
}
