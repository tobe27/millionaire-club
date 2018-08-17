package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;

public interface ClaimMatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimMatch record);

    int insertSelective(ClaimMatch record);

    ClaimMatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimMatch record);

    int updateByPrimaryKey(ClaimMatch record);
}