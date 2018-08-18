package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentProductMapperTest {
@Resource
InvestmentProductMapper investmentProductMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
        InvestmentProduct product = new InvestmentProduct();
        // 产品代号
        product.setProductCode("XSB");
        //产品名称
        product.setName("新手体验计划");
        //还款方式
        product.setRepaymentMode((byte)10);
        product.setValueDate(7);
        product.setStartingAmount(50000);
        product.setDeadline(7);
        product.setMoreDetails("no details for now");
        product.setType(10);
        product.setGmtCreate(System.currentTimeMillis());
      int id=  investmentProductMapper.insertSelective(product);
        System.out.println(id);
    }

    @Test
    public void selectByPrimaryKey() {
        InvestmentProduct product=investmentProductMapper.selectByPrimaryKey(1L);
        System.out.println(product);

    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}