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
    private Long lowerlendingDate;
    private Long upperlendingDate;
    //    出借期限上下限
    private Integer lowerlendingPeriod;
    private Integer upperlendingPeriod;

    //    到期日期上下限
    private Long lowerexpirationDate;
    private Long upperexpirationDate;

    //    出借金额上下限
    private Integer lowerlendingAmount;
    private Integer upperlendingAmount;


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

    public Long getLowerlendingDate() {
        return lowerlendingDate;
    }

    public ClaimInfoQuery setLowerlendingDate(Long lowerlendingDate) {
        this.lowerlendingDate = lowerlendingDate;
        return this;
    }

    public Long getUpperlendingDate() {
        return upperlendingDate;
    }

    public ClaimInfoQuery setUpperlendingDate(Long upperlendingDate) {
        this.upperlendingDate = upperlendingDate;
        return this;
    }

    public Integer getLowerlendingPeriod() {
        return lowerlendingPeriod;
    }

    public ClaimInfoQuery setLowerlendingPeriod(Integer lowerlendingPeriod) {
        this.lowerlendingPeriod = lowerlendingPeriod;
        return this;
    }

    public Integer getUpperlendingPeriod() {
        return upperlendingPeriod;
    }

    public ClaimInfoQuery setUpperlendingPeriod(Integer upperlendingPeriod) {
        this.upperlendingPeriod = upperlendingPeriod;
        return this;
    }

    public Long getLowerexpirationDate() {
        return lowerexpirationDate;
    }

    public ClaimInfoQuery setLowerexpirationDate(Long lowerexpirationDate) {
        this.lowerexpirationDate = lowerexpirationDate;
        return this;
    }

    public Long getUpperexpirationDate() {
        return upperexpirationDate;
    }

    public ClaimInfoQuery setUpperexpirationDate(Long upperexpirationDate) {
        this.upperexpirationDate = upperexpirationDate;
        return this;
    }

    public Integer getLowerlendingAmount() {
        return lowerlendingAmount;
    }

    public ClaimInfoQuery setLowerlendingAmount(Integer lowerlendingAmount) {
        this.lowerlendingAmount = lowerlendingAmount;
        return this;
    }

    public Integer getUpperlendingAmount() {
        return upperlendingAmount;
    }

    public ClaimInfoQuery setUpperlendingAmount(Integer upperlendingAmount) {
        this.upperlendingAmount = upperlendingAmount;
        return this;
    }
}
