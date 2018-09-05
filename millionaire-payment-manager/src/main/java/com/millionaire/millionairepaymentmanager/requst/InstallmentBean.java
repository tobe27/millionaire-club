package com.millionaire.millionairepaymentmanager.requst;
import lombok.Data;
import java.util.List;

/**
 * @author qiaobao
 * @datetime 2018/9/6 2:11
 * @decribe 前台的分期付款的信息返回
 */
@Data
public class InstallmentBean {
    //    起息日期
    private Long valueDateStart;
    //    到息日期
    private Long valueDateEnd;
    //    预期收益
    private Double expectIncome;
    //    用户投资回款信息
    private List<InstallTaskBean> list;



}
