package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowStatisticQuery;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import com.millionaire.millionairebusinessservice.transport.TradingFlowStatisticDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Calendar;
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

            TradingFlow flow = new TradingFlow();
            flow.setUid(8L);
            flow.setInvestmentUserId(2L);
            flow.setName("username");
            flow.setPhone("123456");
            flow.setProductName("mmm1");
            flow.setStatus((byte) 1);
            flow.setBankCardId("bankid");
            flow.setAmount(5000);
            flow.setStatus((byte) 10);
            flow.setType((byte) -1);
            flow.setPayType("bank");
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            flow.setGmtCreate(calendar.getTimeInMillis());
            flow.setGmtUpdate(calendar.getTimeInMillis());
            mapper.insert(flow);

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

    @Test
    public void statisticInvestmentProductByQuery() {
        TradingFlowStatisticQuery query=new TradingFlowStatisticQuery();
        List<TradingFlowStatisticDTO> list=mapper.statisticInvestmentProductByQuery(query);
        System.err.println(list);
    }

    @Test
    public void statisticProductDetailByQuery() {
        TradingFlowDetailQuery query=new TradingFlowDetailQuery();
        List<TradingFlowDetailDTO> list=mapper.statisticProductDetailByQuery(query);
        System.err.println("list = " + list);
    }



}