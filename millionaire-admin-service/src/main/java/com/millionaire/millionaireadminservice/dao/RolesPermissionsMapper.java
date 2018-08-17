package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.RolesPermissions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolesPermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolesPermissions record);

    int insertSelective(RolesPermissions record);

    RolesPermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolesPermissions record);

    int updateByPrimaryKey(RolesPermissions record);
}