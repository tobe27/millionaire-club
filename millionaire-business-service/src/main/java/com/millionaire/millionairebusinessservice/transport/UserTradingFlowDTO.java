package com.millionaire.millionairebusinessservice.transport;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 用户交易信息查询结果包装类
 * @date 2018/8/25 22:04
 */
@Data
public class UserTradingFlowDTO {

private Long gmtCreate;
private String phone;
private String name;
private Long tradingFlowNumber;
    private String productName;
    private Integer amount;

    //    （-1付款，1回款）
    private Byte type;
    //    交易状态
    private Byte status;
    private String bankName;



}
