package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.RolesPermissions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolesPermissionsMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(RolesPermissions record);

    Long insertSelective(RolesPermissions record);

    RolesPermissions selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(RolesPermissions record);

    Long updateByPrimaryKey(RolesPermissions record);

    List<RolesPermissions> findByRoleId(Long id);

}