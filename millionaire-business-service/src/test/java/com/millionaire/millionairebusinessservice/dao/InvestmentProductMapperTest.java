package com.millionaire.millionairebusinessservice.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.ProductQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;

import java.util.List;

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
        //起息日期
        product.setValueDate(10);
        //起投金额
        product.setStartingAmount(50000);
        //期限
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
    public void selectByPage() {
        PageHelper.startPage(1,10);
        List<InvestmentProduct> productList=investmentProductMapper.selectAll();
        PageInfo<InvestmentProduct> pageInfo=new PageInfo<InvestmentProduct>(productList);
        System.out.println(pageInfo);
    }

    @Test
    public void selectProductByPage() {
//       InvestmentProduct product=new InvestmentProduct();
        ProductQuery query=new ProductQuery();
        query.setProductCode("XSB19");
        List<InvestmentProduct> productList=investmentProductMapper.selectProductByPage(query);
        System.err.println(productList);

    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByProductCode() {
        System.out.println(investmentProductMapper.selectByProductCode("XSB"));
    }
}