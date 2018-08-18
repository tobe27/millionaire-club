package com.millionaire.millionaireadminservice.service;

import com.millionaire.millionaireadminservice.module.RolesPermissions;

public interface RolesPermissionsService {
    void deleteByPrimaryKey(Long id);

    long insert(RolesPermissions record);

    long insertSelective(RolesPermissions record);

    RolesPermissions selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(RolesPermissions record);

    long updateByPrimaryKey(RolesPermissions record);
}
