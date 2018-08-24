package com.millionaire.millionaireadminservice.service.impl;


import com.millionaire.millionaireadminservice.dao.PermissionsMapper;
import com.millionaire.millionaireadminservice.module.Permissions;
import com.millionaire.millionaireadminservice.service.PermissionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionsServiceImpl implements PermissionsService {
    @Resource
    private PermissionsMapper permissionsMapper;

    /**
     * 权限是死的
     *
     * @param id
     */
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
        return permissionsMapper.selectByPrimaryKey(id);
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
