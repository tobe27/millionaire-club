package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowStatisticQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import com.millionaire.millionairebusinessservice.transport.TradingFlowStatisticDTO;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 统计模块，用于销量统计查询
 * @date 2018/8/24 18:05
 */
@RestController
@RequestMapping("/a")
public class StatisticsController {
    private Logger logger=LoggerFactory.getLogger(StatisticsController.class);

@Resource
private TradingFlowService tradingFlowService;

@Resource
private InvestmentProductService productService;

    /**
     * @Description 查看投资产品销量统计
     **/
    @GetMapping("/investment-count")
    public ResultBean countInvestmentProduct( @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                       TradingFlowStatisticQuery query) {
        List<TradingFlowStatisticDTO> tradingFlowStatisticDTOS =
                tradingFlowService.statisticInvestmentProductByQuery(query);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<TradingFlowStatisticDTO> pageInfo=new PageInfo<>(tradingFlowStatisticDTOS);
        logger.info("查看投资产品销量统计:{}",query);
        return new ResultBean(1, "success",pageInfo);
    }



    /**
     * @Description 通过交易流水表动态查询投资产品销量统计详细信息
     **/
    @GetMapping("list/investment-detail/{productId}")
    public ResultBean detailInvestmentProduct(@PathVariable("productId")Long id,
                                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                              TradingFlowDetailQuery query){
       // 通过产品id查询产品
        InvestmentProduct product = productService.selectByPrimaryKey(id);
        if (product == null){
          return new ResultBean(-1,"error no such id",id);
        }
        //将产品姓名传入查询参数
        query.setProductName(product.getName());
        List<TradingFlowDetailDTO> tradingFlowDetailDTOS =
                tradingFlowService.statisticProductDetailByQuery(query);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<TradingFlowDetailDTO> pageInfo=new PageInfo<>(tradingFlowDetailDTOS);
        logger.info("查询产品销量统计明细 产品名称:{}",query.getProductName());
        return new ResultBean(1,"success",pageInfo);
    }
}
