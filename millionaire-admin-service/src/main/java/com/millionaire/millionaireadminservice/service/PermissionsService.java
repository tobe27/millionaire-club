package com.millionaire.millionaireadminservice.service;

import com.millionaire.millionaireadminservice.module.Permissions;

public interface PermissionsService {
    void deleteByPrimaryKey(Long id);

    long insert(Permissions record);

    long insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(Permissions record);

    long updateByPrimaryKey(Permissions record);
}
