package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClaimInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimInfo record);

    int insertSelective(ClaimInfo record);

    ClaimInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimInfo record);

    int updateByPrimaryKey(ClaimInfo record);

//    根据债券代码查询
    ClaimInfo selectByCode(String code);
}