package com.millionaire.millionaireserverweb.result;

import lombok.Data;

/**
 * TODO 债权匹配推荐用户投资结果包装类
 * 使用循环添加的方式添加数据
 * by Liu Kai 2018/9/16 20:27
 */
@Data
public class ClaimMatchInvestmentUserDTO {

    private Long id;
    private Long valueDateEnd;
    private String userName;
    private String productName;
    private String lendingContractNumber;
    private Integer investmentAmount;

}
