package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradingFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradingFlow record);

    int insertSelective(TradingFlow record);

    TradingFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradingFlow record);

    int updateByPrimaryKey(TradingFlow record);

    List<TradingFlow> selectByUIDandQuery(TradingFlowQuery query);

}