package com.millionaire.millionairebusinessservice.transport;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class ContractResponse {
    private String idName;
    private String idNumber;
    private String type = "身份证信息";
    private String companySeal;
    private String contractSign;
    private Long gmtCreate;

    @Override
    public String toString() {
        return "ContractResponse{" +
                "idName='" + idName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", type='" + type + '\'' +
                ", companySeal='" + companySeal + '\'' +
                ", contractSign='" + contractSign + '\'' +
                ", gmtCreate=" + gmtCreate +
                '}';
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanySeal() {
        return companySeal;
    }

    public void setCompanySeal(String companySeal) {
        this.companySeal = companySeal;
    }

    public String getContractSign() {
        return contractSign;
    }

    public void setContractSign(String contractSign) {
        this.contractSign = contractSign;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
