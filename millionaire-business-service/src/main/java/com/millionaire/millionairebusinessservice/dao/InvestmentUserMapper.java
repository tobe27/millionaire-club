package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvestmentUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestmentUser record);

    int insertSelective(InvestmentUser record);

    InvestmentUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestmentUser record);

    int updateByPrimaryKey(InvestmentUser record);

//    查询最新的用户id
    Long selectTimeLimit();
}