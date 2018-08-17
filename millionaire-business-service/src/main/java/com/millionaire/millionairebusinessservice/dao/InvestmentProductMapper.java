package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;

public interface InvestmentProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestmentProduct record);

    int insertSelective(InvestmentProduct record);

    InvestmentProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestmentProduct record);

    int updateByPrimaryKey(InvestmentProduct record);
}