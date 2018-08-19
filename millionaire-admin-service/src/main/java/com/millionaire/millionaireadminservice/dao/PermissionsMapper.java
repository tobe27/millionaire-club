package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.Permissions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionsMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(Permissions record);

    Long insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(Permissions record);

    Long updateByPrimaryKey(Permissions record);
}