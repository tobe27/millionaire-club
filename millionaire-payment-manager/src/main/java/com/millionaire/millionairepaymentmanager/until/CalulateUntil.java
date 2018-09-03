package com.millionaire.millionairepaymentmanager.until;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalulateUntil {
    private Logger logger = LoggerFactory.getLogger(CalulateUntil.class);

    private static final Long TIME_DAY = 24 * 60 * 60 * 1000L;
    /**
     * 用户收益的计算公式
     * @param amount
     * @param annualizedIncome
     * @param deadLine
     * @return
     */
    public double incomeCalulate(int amount,double annualizedIncome,int deadLine) {

        logger.info("============================================================================>" +
                     "用户收益的计算公式,投资金额"+amount+"年化收益"+annualizedIncome+"期限"+deadLine);

        BigDecimal amountD = new BigDecimal(Double.toString(amount));
        BigDecimal deadLineD = new BigDecimal(Double.toString(deadLine));
        BigDecimal count = amountD.multiply(deadLineD);

        BigDecimal annualizedIncomeD = new BigDecimal(Double.toString(annualizedIncome));
//         利率的常量
        BigDecimal day = new BigDecimal(Double.toString(360));
//        每日利率计算,采用银行家舍入法，保留10为小数
        BigDecimal annualizedIncomeDay = annualizedIncomeD.divide(day,10,BigDecimal.ROUND_HALF_EVEN);
        logger.info("计算的每日利率"+annualizedIncomeDay);

//        计算投资收益,同时保留两位小数（因为富友支付的金额是以分为单位的，不传小数）
        BigDecimal incomeCalculation = count.multiply(annualizedIncomeDay);
        logger.info("没有四舍五入的投资收益："+incomeCalculation);
        Double newIncomeCalculation = incomeCalculation.setScale(2, RoundingMode.HALF_UP).doubleValue();
        logger.info("四舍五入后的投资收益"+newIncomeCalculation+
                  "<============================================================================");
        return newIncomeCalculation ;
    }

    /**
     * 投资起息时间计算
     * @param valueDateType 起息类型
     * @return
     */
    public Long ValueDateCalculate(Byte valueDateType) {
        logger.info("============================================================================>" +
                "起息类型"+valueDateType);
        Long valueDateStart;                                               //起息类型
        if (valueDateType == 10) {// T+0 当天开始计算利息
            valueDateStart = System.currentTimeMillis();
            return valueDateStart;
        }
       if (valueDateType== 20) { // T+1 开始计算利息
            valueDateStart = System.currentTimeMillis() + TIME_DAY;
            return valueDateStart;
        }
        // T+2 当天开始计算利息
        valueDateStart = System.currentTimeMillis() + TIME_DAY * 2;
        return valueDateStart;


    }
}
