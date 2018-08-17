package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;

public interface ClaimInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimInfo record);

    int insertSelective(ClaimInfo record);

    ClaimInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimInfo record);

    int updateByPrimaryKey(ClaimInfo record);
}