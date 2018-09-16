package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsControllerTest {

    @Resource
    private TradingFlowService tradingFlowService;

    @Test
    public void detailInvestmentProduct() {
        TradingFlowDetailQuery query = new TradingFlowDetailQuery();
        query.setProductName("大富翁");
        List<TradingFlowDetailDTO> tradingFlowDetailDTOS =
                tradingFlowService.statisticProductDetailByQuery(query);
        System.out.println("tradingFlowDetailDTOS = " + tradingFlowDetailDTOS);
    }
}