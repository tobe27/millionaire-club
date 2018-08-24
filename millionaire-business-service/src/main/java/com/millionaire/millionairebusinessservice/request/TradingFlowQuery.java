package com.millionaire.millionairebusinessservice.request;


/**
 * @author Liu Kai
 * @Description: TODO 用于交易流水查询参数包装
 * @date 2018/8/23 21:07
 */

public class TradingFlowQuery {
   private  Long uid;
    private String productName;
    private Byte type;
    private Byte status;
    private Long lowerDate;
    private Long upperDate;


    public TradingFlowQuery() {
    }

    @Override
    public String toString() {
        return "TradingFlowQuery{" +
                "uid=" + uid +
                ", productName='" + productName + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", lowerDate=" + lowerDate +
                ", upperDate=" + upperDate +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public TradingFlowQuery setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public TradingFlowQuery setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Byte getType() {
        return type;
    }

    public TradingFlowQuery setType(Byte type) {
        this.type = type;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public TradingFlowQuery setStatus(Byte status) {
        this.status = status;
        return this;
    }

    public Long getLowerDate() {
        return lowerDate;
    }

    public TradingFlowQuery setLowerDate(Long lowerDate) {
        this.lowerDate = lowerDate;
        return this;
    }

    public Long getUpperDate() {
        return upperDate;
    }

    public TradingFlowQuery setUpperDate(Long upperDate) {
        this.upperDate = upperDate;
        return this;
    }
}
