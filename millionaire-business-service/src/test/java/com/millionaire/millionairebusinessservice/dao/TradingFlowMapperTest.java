package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TradingFlowMapperTest {
@Resource
    private TradingFlowMapper mapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        for (int i = 1; i < 5; i++) {
            TradingFlow flow = new TradingFlow();
            flow.setUid(2L);
            flow.setName("username");
            flow.setPhone("123456");
            flow.setProductName("productname");
            flow.setStatus((byte) 1);
            flow.setBankCardId("bankid");
            flow.setAmount(5000);
            flow.setStatus((byte) 10);
            flow.setType((byte) 1);
            flow.setPayType("bank");
            flow.setGmtCreate(System.currentTimeMillis());
            flow.setGmtUpdate(System.currentTimeMillis());
            mapper.insert(flow);
        }
    }
    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByUIDandQuery() {
        TradingFlowQuery query = new TradingFlowQuery();
         query.setProductName("productname");
        query.setUid(1L);
        List<TradingFlow> list = mapper.selectByUIDandQuery(query);
        System.out.println("list = " + list);
    }
}