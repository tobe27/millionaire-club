package com.millionaire.millionairebusinessservice.transport;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品交易统计明细查询参数包装返回参数包装
 * @date 2018/8/25 13:35
 */
@Data
public class TradingFlowDetailDTO {

    private  String purchaseDate;

   /** 购买人数*/
    private Integer purchaseNumber;

    /**
     * 购买次数
     */
    private Integer purchase;
    /**  购买总金额 */
    private Integer totalPurchases;
}
