package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestmentUserInsertTest {

    @Test
    public void investmentUserInsert() {
        UserInvestmentRequestBean requestBean = new UserInvestmentRequestBean();
        requestBean.setProductId(0L);
        requestBean.setUserBankId(4l);
        requestBean.setAmount(1000);
        requestBean.setContractSign("dgfsgfdsdgsf");

    }
}