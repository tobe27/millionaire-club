package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentProductImplTest {

    @Resource
    private InvestmentProductService investmentProduct;
    //添加产品返回id
    @Test
    public void insertProductSelective() {
        for(int i=2;i<20;i++) {
            InvestmentProduct product = new InvestmentProduct();
            // 产品代号
            product.setProductCode("XSB"+i);
            //产品名称
            product.setName("新手体验计划"+i);
            //还款方式
            product.setRepaymentMode((byte) 10);
            product.setValueDate(7);
            product.setStartingAmount(50000);
            product.setDeadline(7);
            product.setMoreDetails("no details for now");
            product.setType(10);
           Long id= investmentProduct.insertProductSelective(product);
            System.out.println(id);
        }
    }

    @Test
    public void updateProductByPrimaryKeyLimit() {
        investmentProduct.updateProductByPrimaryKeyLimit(1L,20,(byte)0,(byte)0);

    }

    @Test
    public void selectByPage() {
        PageInfo<InvestmentProduct> pageInfo=investmentProduct.selectByPage(1,10);
        System.err.println(pageInfo);
    }
}