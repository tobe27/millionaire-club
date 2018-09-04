package com.millionaire.millionairebusinessservice.transport;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;
import lombok.Setter;

/**
 * @author Liu Kai
 * @Description: TODO 用户交易记录参数包装类
 * 重写未分配收益set方法
 * undistributedIncome = expectedIncome -distributedIncome
 * @date 2018/8/24 10:07
 */

@Data
public class InvestmentUserDTO {
   private  Long id;

    /**
     * 起息时间
     **/
    private Long valueDateStart;

    /**
     * 到息时间
     **/
    private Long valueDateEnd;

    /**
     * 手机号
     **/
    private Long phone;

    /**
     * 用户名
     **/
    private String userName;

    /**
     * 投资产品名称
     */
    private String productName;

    /**
     * 出借合同编号
     */
    private String lendingContractNumber;

    /**
     * 投资金额
     */
    private Integer investmentAmount;

    /**
     * 已分配收益
     */
    private Double distributedIncome;


    /**
     * 未分配收益
     */
    private Double undistributedIncome;


    /**
     * 投资状态
     */
    private Byte investmentStatus;

    /**
     * 债权协议编号
     */
    private String creditContractNumber;

}
