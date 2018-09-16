package com.millionaire.millionairebusinessservice.request;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品销售统计查询参数包装类 查询交易流水表
 * @date 2018/8/25 9:03
 */
@Data
public class TradingFlowStatisticQuery {

    private String productName;
    private String productCode;
    /** 交易类型=-1 表示付款 */
    private  static  Byte type = -1;
    /**  交易状态=10 表示交易成功*/
    private  static   Byte status = 10;
}
