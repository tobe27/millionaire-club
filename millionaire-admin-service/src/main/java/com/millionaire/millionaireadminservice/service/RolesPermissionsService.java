package com.millionaire.millionaireadminservice.service;

import com.millionaire.millionaireadminservice.module.RolesPermissions;

import java.util.List;

public interface RolesPermissionsService {
    void deleteByPrimaryKey(Long id);

    long insert(RolesPermissions record);

    long insertSelective(RolesPermissions record);

    RolesPermissions selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(RolesPermissions record);

    long updateByPrimaryKey(RolesPermissions record);

    List<RolesPermissions> findByRoleId(Long id);
}
