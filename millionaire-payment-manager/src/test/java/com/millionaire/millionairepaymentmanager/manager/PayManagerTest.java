package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PayManagerTest {

    @Autowired
    private PayManager payManager;

    @Test
    public void payment() throws IOException, FuYouException {
        UserInvestmentRequestBean userInvestmentRequestBean = new UserInvestmentRequestBean();
        userInvestmentRequestBean.setProductId(53L);
        userInvestmentRequestBean.setUserBankId(7L);
        userInvestmentRequestBean.setAmount(100000);
        userInvestmentRequestBean.setContractSign("kljhhkkk");

        payManager.payment(userInvestmentRequestBean,8L);
    }
}