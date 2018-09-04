package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowStatisticQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import com.millionaire.millionairebusinessservice.transport.TradingFlowStatisticDTO;
import com.millionaire.millionaireserverweb.result.ResultBean;
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
    @Resource
    private InvestmentProductService productService;
    
    @Test
    public void countInvestmentProduct() {
        TradingFlowStatisticQuery query = new TradingFlowStatisticQuery();
        List<TradingFlowStatisticDTO> tradingFlowStatisticDTOS =
                tradingFlowService.statisticInvestmentProductByQuery(query);
        System.out.println("tradingFlowStatisticDTOS = " + tradingFlowStatisticDTOS);
    }

    @Test
    public void detailInvestmentProduct() {
        InvestmentProduct product = productService.selectByPrimaryKey(2L);
        TradingFlowDetailQuery query = new TradingFlowDetailQuery();
        //将产品姓名传入查询参数
        query.setProductName(product.getName());
        List<TradingFlowDetailDTO> tradingFlowDetailDTOS =
                tradingFlowService.statisticProductDetailByQuery(query);
        System.err.println("tradingFlowDetailDTOS = " + tradingFlowDetailDTOS);
    }
}