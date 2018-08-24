package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.TradingFlow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradingFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradingFlow record);

    int insertSelective(TradingFlow record);

    TradingFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradingFlow record);

    int updateByPrimaryKey(TradingFlow record);
}