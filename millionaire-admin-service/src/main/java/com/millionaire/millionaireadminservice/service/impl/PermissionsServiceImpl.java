package com.millionaire.millionaireadminservice.service.impl;

import com.millionaire.millionaireadminservice.module.Permissions;
import com.millionaire.millionaireadminservice.service.PermissionsService;

public class PermissionsServiceImpl implements PermissionsService {
    @Override
    public void deleteByPrimaryKey(Long id) {
    }

    @Override
    public long insert(Permissions record) {
        return 0;
    }

    @Override
    public long insertSelective(Permissions record) {
        return 0;
    }

    @Override
    public Permissions selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public long updateByPrimaryKeySelective(Permissions record) {
        return 0;
    }

    @Override
    public long updateByPrimaryKey(Permissions record) {
        return 0;
    }
}
