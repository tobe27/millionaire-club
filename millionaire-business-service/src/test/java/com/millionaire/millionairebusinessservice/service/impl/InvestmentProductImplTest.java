package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.ProductQuery;
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
    private InvestmentProductService productService;
    @Resource
    private InvestmentProductMapper productMapper;

    //添加产品返回id
    @Test
    public void insertProductSelective() {
//        for (int i = 22; i < 40; i++) {
//            InvestmentProduct product = new InvestmentProduct();
//            // 产品代号
//            product.setProductCode("XSB" + i);
//            //产品名称
//            product.setName("新手体验计划" + i);
//            //还款方式
//            product.setRepaymentMode((byte) 10);
//            product.setValueDate(10);
//            product.setStartingAmount(50000);
//            product.setDeadline(7);
//            product.setMoreDetails("no details for now");
//            product.setType(10);
//            Long id = productService.insertProductSelective(product);
//            System.out.println(id);
//        }
        InvestmentProduct product = new InvestmentProduct();
            // 产品代号
            product.setProductCode("XSBy" );
            //产品名称
            product.setName("体验计划" );
            //还款方式
            product.setRepaymentMode((byte) 10);
            product.setValueDate(10);
            product.setStartingAmount(50000);
            product.setDeadline(7);
            product.setMoreDetails("no details for now");
            product.setType(10);
            product.setIsRecommend((byte)0);
            product.setIsShelf((byte)0);
            product.setIsPurchaseLimit((byte)0);
            productService.insertProductSelective(product);
            Long id=product.getId();
            System.out.println(id);

    }

    @Test
    public void updateProductByPrimaryKeySelective() throws Exception {
        for (long i = 1; i < 40; i++) {
            InvestmentProduct product = productMapper.selectByPrimaryKey(i);
            if (product == null) {
                i++;
            } else {
                if (i <= 10) {
                    product.setAnnualizedIncome(0.5);
                    product.setRepaymentMode((byte) 20);
                    product.setValueDate(10);
                    product.setDeadline(500);
                    product.setIsRecommend((byte) 1);
                    product.setIsShelf((byte) 1);
                    product.setIsPurchaseLimit((byte) 1);
                    productService.updateProductByPrimaryKeySelective(product);
                }
                if (i > 10 && i <= 20) {
                    product.setAnnualizedIncome(0.40);
                    product.setRepaymentMode((byte) 20);
                    product.setValueDate(20);
                    product.setDeadline(100);
                    product.setIsRecommend((byte) 1);
                    product.setIsShelf((byte) 1);
                    product.setIsPurchaseLimit((byte) 0);
                    productService.updateProductByPrimaryKeySelective(product);
                }
                if (i > 20 && i <= 30) {
                    product.setAnnualizedIncome(0.30);
                    product.setRepaymentMode((byte) 30);
                    product.setValueDate(20);
                    product.setDeadline(40);
                    product.setIsRecommend((byte) 1);
                    product.setIsShelf((byte) 0);
                    product.setIsPurchaseLimit((byte) 0);
                    productService.updateProductByPrimaryKeySelective(product);
                }
                if (i > 30 && i <= 40) {
                    product.setAnnualizedIncome(0.20);
                    product.setRepaymentMode((byte) 10);
                    product.setValueDate(30);
                    product.setDeadline(5);
                    product.setIsRecommend((byte) 0);
                    product.setIsShelf((byte) 0);
                    product.setIsPurchaseLimit((byte) 0);
                    productService.updateProductByPrimaryKeySelective(product);
                }
            }
        }
    }

    @Test
    public void updateProductByPrimaryKeyLimit() {

        productService.updateProductByPrimaryKeyLimit(1L, 20, (byte) 0, (byte) 0);

    }

    @Test
    public void selectByPage() {
        PageInfo<InvestmentProduct> pageInfo = productService.selectByPage(1, 10);
        System.err.println(pageInfo);
    }


    @Test
    public void selectProductByPage() throws Exception {
//       InvestmentProduct product=new InvestmentProduct();
        ProductQuery query = new ProductQuery();
        query.setName("新");
        query.setProductCode("1");
        query.setUpperAnnualizedIncome(0.2);
        query.setLowerAnnualizedIncome(0.1);
        query.setValueDate(10);
//        query.setIsShelf((byte)2);
//        query.setLowerDeadline(4);
//        query.setUpperDeadline(5);
        PageInfo<InvestmentProduct> pageInfo = productService.selectProductByPage(query, 1, 1);
         System.err.println(pageInfo);
    }



}