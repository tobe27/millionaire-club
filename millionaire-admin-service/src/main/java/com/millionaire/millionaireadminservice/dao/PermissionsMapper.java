package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.Permissions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);
}