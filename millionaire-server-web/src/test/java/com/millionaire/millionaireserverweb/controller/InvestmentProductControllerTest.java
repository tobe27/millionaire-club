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

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentProductControllerTest {
   @Autowired
   InvestmentProductService service;

   @Autowired
    InvestmentProductMapper mapper;

    @Test
    public void getListInvestmentProduct() {
        PageInfo<InvestmentProduct> pageInfo=service.selectByPage(1,1);
        System.err.println(pageInfo);
    }

    @Test
    public void mapperTest() {
        List<InvestmentProduct> list=mapper.selectAll();
        System.out.println(list);
    }


}
