package com.millionaire.millionairepaymentmanager;

import com.millionaire.millionairepaymentmanager.manager.InvestmentUserInsert;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;

public class PayMananger {

    InvestmentUserInsert investmentUserInsert = new InvestmentUserInsert();


    private void payManager(UserInvestmentRequestBean requestBean, long uid) {
//        生成用户投资信息
        investmentUserInsert.investmentUserInsert(requestBean, uid);
//        生成交易记录
//        用户消息推送
//        调用支付接口

    }




}
