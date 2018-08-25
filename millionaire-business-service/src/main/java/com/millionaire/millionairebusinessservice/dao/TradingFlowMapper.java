package com.millionaire.millionairebusinessservice.dao;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowStatisticQuery;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import com.millionaire.millionairebusinessservice.transport.TradingFlowStatisticDTO;
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

    //    根据用户id查询交易流水
    List<TradingFlow> fingByUid(Long uid);

/**
 * @Description 通过交易流水表动态查询投资产品销量统计
 **/
List<TradingFlowStatisticDTO> statisticInvestmentProductByQuery(TradingFlowStatisticQuery query);

/**
 * @Description 通过交易流水表动态查询投资产品销量统计详细信息
 **/
List<TradingFlowDetailDTO> statisticProductDetailByQuery(TradingFlowDetailQuery query);


}