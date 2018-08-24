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
    public void name() {
        InvestmentProduct product = new InvestmentProduct();
        // 产品代号
        product.setProductCode("XSB1" );
        //产品名称
        product.setName("体验计划" );
        //还款方式
        product.setRepaymentMode((byte) 10);
        product.setValueDate((byte)10);
        product.setStartingAmount(50000);
        product.setDeadline(7);
        product.setMoreDetails("no details for now");
        product.setType((byte)10);
        product.setIsRecommend((byte)0);
        product.setIsShelf((byte)0);
        product.setIsPurchaseLimit((byte)0);
        service.insertProductSelective(product);
        Long id=product.getId();
        System.out.println(id);
    }
}
