package com.millionaire.millionairebusinessservice.transport;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品销售统计查询结果包装类 查询交易流水表
 * @date 2018/8/25 10:36
 */
@Data
public class TradingFlowStatisticDTO {
    private Long productId;
    private String productCode;
    private String productName;
    /**
     * 购买人数
     */
    private Integer purchaseNumber;

    /**
     * 购买次数
     */
    private Integer purchase;
    /**  购买总金额 */
    private Integer totalPurchases;

}
