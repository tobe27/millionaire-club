package com.millionaire.millionairebusinessservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowStatisticQuery;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import com.millionaire.millionairebusinessservice.transport.TradingFlowStatisticDTO;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/23 20:31
 */
public interface TradingFlowService {

//    int deleteByPrimaryKey(Long id);
//
//    int insert(TradingFlow record);
//
//    int insertSelective(TradingFlow record);
//
//    TradingFlow selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(TradingFlow record);
//
//    int updateByPrimaryKey(TradingFlow record);

    PageInfo<TradingFlow> selectTradingFlowBypage(Integer pageNum, Integer PageSize, TradingFlowQuery query);

    /**
     * @Description 通过交易流水表动态查询投资产品销量统计
     **/
    List<TradingFlowStatisticDTO> statisticInvestmentProductByQuery(TradingFlowStatisticQuery query);

    /**
     * @Description 通过交易流水表动态查询投资产品销量统计详细信息
     **/
    List<TradingFlowDetailDTO> statisticProductDetailByQuery(TradingFlowDetailQuery query);


}
