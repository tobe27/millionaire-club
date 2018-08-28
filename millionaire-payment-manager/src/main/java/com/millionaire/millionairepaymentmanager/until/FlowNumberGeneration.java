package com.millionaire.millionairepaymentmanager.until;

import java.time.LocalDate;

/**
 * 协议和用户编号的生成工具类，在插入数据的时候调用
 */
public class FlowNumberGeneration {

    private static final String HEAD = "UK";
    private static final String CLAIM_HEAD = "UKZQ";
    private static final String CONSTANTS_NUM = "10";

//    年份的后两位,String 的format方法:年的后两位数字（不足两位前面补0）：%ty
    private static String currentYear = String.format("%ty", LocalDate.now());
//    流水编号
    private static String no;

    /**
     * 用户编号：UK+年份后两位+10+000001（6位递增数字）

     * @return
     */
    public static String userProtocol(int currentNumber){

        //String 的format方法，("%04d", 99)数字前面补0
        no = String.format("%06d",currentNumber);

        return new String(HEAD+currentYear+CONSTANTS_NUM+no);
    }

    /**
     * 出借合同编号：UK+产品代号+年份后两位+10+000001（6位递增数字）
     * @param productCode
     * @param currentNumber
     * @return
     */
    public static String lendProtocol(String productCode,Long currentNumber) {

        //String 的format方法，("%04d", 99)数字前面补0
        no = String.format("%06d",currentNumber);

        return new String(HEAD+productCode+currentYear+CONSTANTS_NUM+no);
    }

    /**
     * 债权协议编号：UK+ZQ+年份后两位+10+000001（6位递增数字）
     * @param currentNumber
     * @return
     */
    public static String claimProtocol(long currentNumber) {

        //String 的format方法，("%04d", 99)数字前面补0
        no = String.format("%06d",currentNumber);

        return new String(CLAIM_HEAD+currentYear+CONSTANTS_NUM+no);
    }


    public static void main(String[] args) {
        System.out.println(claimProtocol(10L));
    }

}
