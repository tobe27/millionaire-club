package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestmentUserMapperTest {
    @Resource
    private InvestmentUserMapper investmentUserMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        for (int i = 0; i <10 ; i++) {
            InvestmentUser investmentUser = new InvestmentUser();

            investmentUser.setProductId(2L);
            investmentUser.setUid(3L);
            investmentUser.setValueDateStart(56566L);
            investmentUser.setValueDateEnd(5655454L);
            investmentUser.setLendingContractNumber("546546546");
            investmentUser.setContractSign("546546546546"+i);
            investmentUser.setInvestmentAmount(5055);
            investmentUser.setInvestmentStatus((byte)1);
            investmentUser.setExpectedIncome(555.1564D);
            investmentUser.setDistributedIncome(5666.515560D);
            investmentUser.setGmtCreate(System.currentTimeMillis());
            investmentUser.setGmtUpdate(System.currentTimeMillis());
            investmentUserMapper.insert(investmentUser);
            System.out.println(investmentUser.getId());
        }
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectTimeLimit() {
        System.out.println(investmentUserMapper.selectTimeLimit());

    }
}