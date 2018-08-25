package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.TradingFlowMapper;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
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

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public Long insert(TradingFlow record) {
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        logger.info("交易信息插入："+record);
        flowMapper.insert(record);
        return record.getId();
    }

    @Override
    public int insertSelective(TradingFlow record) {
        return 0;
    }

    @Override
    public TradingFlow selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TradingFlow record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TradingFlow record) {
        return 0;
    }

    @Override
    public PageInfo<TradingFlow> selectTradingFlowBypage(Integer pageNum, Integer PageSize, TradingFlowQuery query) {
        PageHelper.startPage(pageNum,PageSize);
        List<TradingFlow> list=flowMapper.selectByUIDandQuery(query);
        return new PageInfo<>(list);
    }
}
