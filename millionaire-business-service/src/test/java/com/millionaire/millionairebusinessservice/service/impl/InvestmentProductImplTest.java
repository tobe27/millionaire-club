package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.ProductQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvestmentProductImplTest {
    @Autowired
    private InvestmentProductService investmentProductService;

    @Test
    public void insertProductSelective() {
        InvestmentProduct investmentProduct = new InvestmentProduct();
        investmentProduct.setProductCode("mm1");
        investmentProduct.setName("mmm1");
        investmentProduct.setAnnualizedIncome(0.15D);
        investmentProduct.setRepaymentMode((byte)1);
        investmentProduct.setStartingAmount(100000);
        investmentProduct.setValueDate((byte)1);
        investmentProduct.setDeadline(30);
        investmentProduct.setDescribe("nnn");
        investmentProduct.setMoreDetails("nn");
        investmentProduct.setType((byte)1);
        investmentProduct.setIsRecommend((byte)1);
        investmentProduct.setIsPurchaseLimit((byte)1);
        investmentProduct.setIsShelf((byte)1);
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(investmentProductService.insertProductSelective(investmentProduct));
        System.out.println("===========================================邪魔退散===========================================");

    }

    @Test
    public void updateProductByPrimaryKeySelective() {
        InvestmentProduct investmentProduct = new InvestmentProduct();
        investmentProduct.setId(48L);
        investmentProduct.setIsRecommend((byte)0);
        investmentProduct.setIsShelf((byte)0);
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(investmentProductService.updateProductByPrimaryKeySelective(investmentProduct));
        System.out.println("===========================================邪魔退散===========================================");
    }

    @Test
    public void updateProductByPrimaryKeyLimit() {
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(investmentProductService.selectByPrimaryKey(48L));
        System.out.println("===========================================邪魔退散===========================================");
    }

    @Test
    public void selectByPage() {
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(investmentProductService.selectByPage(3, 1));
        System.out.println("===========================================邪魔退散===========================================");
    }

    @Test
    public void selectProductByPage() {
        ProductQuery query = new ProductQuery();
        query.setName("新");
        query.setProductCode("1");
        query.setUpperAnnualizedIncome(0.2);
        query.setLowerAnnualizedIncome(0.1);
        query.setValueDate((byte)10);
        System.out.println("===========================================佛主保佑===========================================");
        System.out.println(investmentProductService.selectProductByPage(query,3, 1));
        System.out.println("===========================================邪魔退散===========================================");
    }

    @Test
    public void selectByProductName() {
    }

    @Test
    public void selectByProductCode() {
    }
    

}