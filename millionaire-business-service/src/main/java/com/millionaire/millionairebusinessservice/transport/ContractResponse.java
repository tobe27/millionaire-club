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
}
