package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClaimMatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimMatch record);

    int insertSelective(ClaimMatch record);

    ClaimMatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimMatch record);

    int updateByPrimaryKey(ClaimMatch record);
}