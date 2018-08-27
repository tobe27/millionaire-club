package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestmentUserServiceImplTest {
@Resource
private InvestmentUserService investmentUserService;


    @Test
    public void insert() {
        InvestmentUser investmentUser= new InvestmentUser();
//        investmentUser.setClaimId(0L);
//        investmentUser.setId(0L);
        investmentUser.setProductId(51L);
//        investmentUser.setUid(0L);
        investmentUser.setValueDateStart(System.currentTimeMillis());
        investmentUser.setValueDateEnd(0L);
        investmentUser.setLendingContractNumber("");
        investmentUser.setContractSign("");
        investmentUser.setInvestmentAmount(0);
        investmentUser.setInvestmentStatus((byte)0);
        investmentUser.setExpectedIncome(0.0D);
        investmentUser.setDistributedIncome(0.0D);

       investmentUserService.insert(investmentUser);



    }

    @Test
    public void selectTimeLimit() {
    }

    @Test
    public void listInvestmentUserByUID() {
    }

    @Test
    public void listInvestmentUserByQuery() {
    }
}