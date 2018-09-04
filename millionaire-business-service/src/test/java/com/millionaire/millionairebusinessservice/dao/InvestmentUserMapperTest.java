package com.millionaire.millionairebusinessservice.dao;

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
    public void selectExistNovicePlan() {
        System.out.println(investmentUserMapper.selectExistNovicePlan(1L));
    }

    @Test
    public void selectRenewalInvestmentById() {


        System.out.println(hashCode());

    }
}