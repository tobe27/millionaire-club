package com.millionaire.millionairebusinessservice.request;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 债券与用户投资的匹配情况查询参数包装类
 * @date 2018/8/27 12:32
 */
@Data
public class ClaimMatchQuery {

    private Long claimID;
     // 债权匹配状态 1=有效
    private Byte status = 1;

    private String productName;
    private String lendingContractNumber;
    private  Long valueStartlowerDate;
    private Long valueStartupperDate;
    private  Long valueEndlowerDate;
    private Long valueEndupperDate;
    private  String userName;

}
