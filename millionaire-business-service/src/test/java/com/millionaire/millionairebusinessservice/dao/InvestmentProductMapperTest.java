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

import java.util.Date;
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
        product.setProductCode("XSB21");
        //产品名称
        product.setName("新手体验计划21");
        //年化收益
        product.setAnnualizedIncome(0.12);
        //还款方式
        product.setRepaymentMode((byte)10);
        //起息日期
        product.setValueDate(10);
        //起投金额
        product.setStartingAmount(50000);
        //期限
        product.setDeadline(7);
        product.setRemark("备注信息");
        product.setMoreDetails("no details for now");
        product.setType(10);
        product.setIsRecommend((byte)1);
        product.setIsPurchaseLimit((byte)1);
        product.setIsShelf((byte)1);
        product.setGmtCreate(System.currentTimeMillis());
        product.setGmtUpdate(System.currentTimeMillis());
        investmentProductMapper.insertSelective(product);
        System.out.println(product.getId());
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
        System.out.println(productList);
//        PageInfo<InvestmentProduct> pageInfo=new PageInfo<InvestmentProduct>(productList);
//        System.out.println(pageInfo);
    }

    @Test
    public void selectProductByPage() {
//       InvestmentProduct product=new InvestmentProduct();
//        ProductQuery query=new ProductQuery();
//        query.setName("新");
//        query.setProductCode("1");
//        query.setUpperAnnualizedIncome(0.2);
//        query.setLowerAnnualizedIncome(0.1);
//        query.setValueDate(10);
//        query.setIsShelf((byte)2);
//        query.setLowerDeadline(4);
//        query.setUpperDeadline(5);
//        List<InvestmentProduct> productList=investmentProductMapper.selectProductByPage(query);
//        System.err.println(productList);

        Date date = new Date(20180706);
        System.err.println(date);

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