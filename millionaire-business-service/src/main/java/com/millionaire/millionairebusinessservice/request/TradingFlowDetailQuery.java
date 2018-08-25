package com.millionaire.millionairebusinessservice.request;

import lombok.Data;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品交易统计明细查询参数包装
 * @date 2018/8/25 12:48
 */

public class TradingFlowDetailQuery {

    private String productName;
    /**
     * 交易类型=-1 表示付款
     */
    private static Byte type = -1;
    /**
     * 交易状态=10 表示交易成功
     */
    private static Byte status = 10;

    private Long lowerDate;

    private Long upperDate;


    public TradingFlowDetailQuery() {
    }

    public TradingFlowDetailQuery(String productName, Long lowerDate, Long upperDate) {
        this.productName = productName;
        this.lowerDate = lowerDate;
        this.upperDate = upperDate;
    }

    @Override
    public String toString() {
        return "TradingFlowDetailQuery{" +
                "productName='" + productName + '\'' +
                ", lowerDate=" + lowerDate +
                ", upperDate=" + upperDate +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public TradingFlowDetailQuery setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public static Byte getType() {
        return type;
    }

    public static void setType(Byte type) {
        TradingFlowDetailQuery.type = type;
    }

    public static Byte getStatus() {
        return status;
    }

    public static void setStatus(Byte status) {
        TradingFlowDetailQuery.status = status;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public TradingFlowDetailQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public TradingFlowDetailQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }
}
