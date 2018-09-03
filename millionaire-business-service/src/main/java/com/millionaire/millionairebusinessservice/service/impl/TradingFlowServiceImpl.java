package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.TradingFlowMapper;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowDetailQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowStatisticQuery;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.TradingFlowDetailDTO;
import com.millionaire.millionairebusinessservice.transport.TradingFlowStatisticDTO;
import com.millionaire.millionairebusinessservice.transport.UserTradingFlowDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/23 20:31
 */
@Service
public class TradingFlowServiceImpl  implements TradingFlowService {

    private Logger logger = LoggerFactory.getLogger(TradingFlowServiceImpl.class);
    @Resource
    private TradingFlowMapper flowMapper;

//    @Override
//    public int deleteByPrimaryKey(Long id) {
//        return 0;
//    }
//
//    @Override
//    public int insert(TradingFlow record) {
//        return 0;
//    }
//
//    @Override
//    public int insertSelective(TradingFlow record) {
//        return 0;
//    }
//
//    @Override
//    public TradingFlow selectByPrimaryKey(Long id) {
//        return null;
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(TradingFlow record) {
//        return 0;
//    }
//
//    @Override
//    public int updateByPrimaryKey(TradingFlow record) {
//        return 0;
//    }


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public Long insert(TradingFlow record) {
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        flowMapper.insert(record);
        logger.info("交易信息插入："+record+
                "<=====================================================================================");
        return record.getId();
    }







    @Override
    public PageInfo<UserTradingFlowDTO> selectTradingFlowBypage(Integer pageNum, Integer PageSize, TradingFlowQuery query) {
        PageHelper.startPage(pageNum,PageSize);
        List<UserTradingFlowDTO> list=flowMapper.selectByUIDandQuery(query);
        return new PageInfo<>(list);
    }


    /**
     * @param query TradingFlowStatisticQuery 交易统计查询参数
     * @Description 通过交易流水表动态查询投资产品销量统计
     */
    @Override
    public List<TradingFlowStatisticDTO> statisticInvestmentProductByQuery(TradingFlowStatisticQuery query) {
        return flowMapper.statisticInvestmentProductByQuery(query);
    }

    /**
     * @param query TradingFlowDetailQuery 交易明细查询参数
     * @Description 通过交易流水表动态查询投资产品销量统计详细信息
     */
    @Override
    public List<TradingFlowDetailDTO> statisticProductDetailByQuery(TradingFlowDetailQuery query) {
        return flowMapper.statisticProductDetailByQuery(query);
    }

    @Override
    public int updateTradingFlowStatus(Long investmentUserId, Byte status) {
        return flowMapper.updateTradingFlowStatus(investmentUserId,status,System.currentTimeMillis());
    }

    @Override
    public List<TradingFlow> findByUid(Long uid) {
        return flowMapper.findByUid(uid);
    }

    @Override
    public TradingFlow selectByPrimaryKey(Long id) {
        return flowMapper.selectByPrimaryKey(id);
    }
}
