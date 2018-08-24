package com.millionaire.millionairebusinessservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/23 20:31
 */
public interface TradingFlowService {

    int deleteByPrimaryKey(Long id);

    int insert(TradingFlow record);

    int insertSelective(TradingFlow record);

    TradingFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradingFlow record);

    int updateByPrimaryKey(TradingFlow record);

    PageInfo<TradingFlow> selectTradingFlowBypage(Integer pageNum, Integer PageSize, TradingFlowQuery query);
}
