package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentProductImplTest {

@Resource InvestmentProductImpl insertProductSelective;
    //添加产品返回id
    @Test
    public void insertProductSelective() {
        InvestmentProduct product = new InvestmentProduct();
        // 产品代号
        product.setProductCode("XSB1");
        //产品名称
        product.setName("新手体验计划1");
        //还款方式
        product.setRepaymentMode((byte)10);
        product.setValueDate(7);
        product.setStartingAmount(50000);
        product.setDeadline(7);
        product.setMoreDetails("no details for now");
        product.setType(10);
       insertProductSelective.insertProductSelective(product);
        System.out.println(product.getId());
    }
}