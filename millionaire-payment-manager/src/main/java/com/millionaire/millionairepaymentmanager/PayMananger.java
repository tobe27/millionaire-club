package com.millionaire.millionairepaymentmanager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairepaymentmanager.manager.InvestmentUserInsert;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import com.millionaire.millionairepaymentmanager.until.FlowNumberGeneration;
import com.sun.tools.javac.comp.Flow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PayMananger {

    InvestmentUserInsert investmentUserInsert = new InvestmentUserInsert();


    private void payManager(UserInvestmentRequestBean requestBean, long uid) {
//        生成用户投资信息
        investmentUserInsert.investmentUserInsert(requestBean, uid);
//        调用支付接口

    }




}
