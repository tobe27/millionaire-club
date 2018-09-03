package com.millionaire.millionaireserverweb.controller;


import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.assertj.core.internal.Integers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentProductControllerTest {
   @Autowired
   InvestmentProductService service;

   @Resource
    InvestmentProductMapper mapper;

    @Test
    public void getListInvestmentProduct() {
//        PageInfo<InvestmentProduct> pageInfo=service.selectByPage(1,1);
//        System.err.println(pageInfo);
        InvestmentProduct product=service.selectByPrimaryKey(1L);
        System.out.println(product);
        InvestmentProduct product1=mapper.selectByPrimaryKey(1L);
        System.out.println("product1 = " + product1);
    }

    @Test
    public void mapperTest() {
        List<InvestmentProduct> list=mapper.selectAll();
        System.out.println(list);
    }

    @Test
    public void insertProduct() {
        InvestmentProduct product = new InvestmentProduct();
        for(int i = 0;i<9;i++) {
            if (i < 3) {
                product.setProductCode("newProductCode" + i);
                product.setName("newProductName" + i);
                product.setAnnualizedIncome(i * 0.01);
                product.setRepaymentMode((byte) 10);
                product.setStartingAmount(i * 10000);
                product.setValueDate((byte) 10);
                product.setDeadline(i * 10);
                product.setDescribe("备注" + i);
                product.setMoreDetails("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
                product.setType((byte) 10);
                product.setIsRecommend((byte) 1);
                product.setIsPurchaseLimit((byte) 1);
                product.setIsShelf((byte) 1);
                service.insertProductSelective(product);
            } else if (i < 6) {
                product.setProductCode("newProductCode" + i);
                product.setName("newProductName" + i);
                product.setAnnualizedIncome(i * 0.01);
                product.setRepaymentMode((byte) 20);
                product.setStartingAmount(i * 10000);
                product.setValueDate((byte) 20);
                product.setDeadline(i * 30);
                product.setDescribe("备注" + i);
                product.setMoreDetails("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
                product.setType((byte) 20);
                product.setIsRecommend((byte) 1);
                product.setIsPurchaseLimit((byte) 1);
                product.setIsShelf((byte) 1);
                service.insertProductSelective(product);
            } else {
                product.setProductCode("newProductCode" + i);
                product.setName("newProductName" + i);
                product.setAnnualizedIncome(i * 0.01);
                product.setRepaymentMode((byte) 20);
                product.setStartingAmount(i * 10000);
                product.setValueDate((byte) 30);
                product.setDeadline(i * 30);
                product.setDescribe("备注" + i);
                product.setMoreDetails("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
                product.setType((byte) 30);
                product.setIsRecommend((byte) 1);
                product.setIsPurchaseLimit((byte) 1);
                product.setIsShelf((byte) 1);
                service.insertProductSelective(product);
            }

        }
    }
}
