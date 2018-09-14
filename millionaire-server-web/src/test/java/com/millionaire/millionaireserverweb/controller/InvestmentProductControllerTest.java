package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentProductControllerTest {

    @Resource
    InvestmentProductService investmentProductService;

    @Test
    public void update() {
        InvestmentProduct product =investmentProductService.selectByPrimaryKey(2L);
        product.setIsShelf((byte)0);
        investmentProductService.updateProductByPrimaryKeySelective(product);
    }
}