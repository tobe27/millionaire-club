package com.millionaire.millionaireadminservice.service.impl;

import com.millionaire.millionaireadminservice.dao.BackstageUsersMapper;
import com.millionaire.millionaireadminservice.module.BackstageUsers;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BackstageUsersServiceImpl implements BackstageUsersService {
    @Resource
    private BackstageUsersMapper backstageUsersMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        backstageUsersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public long insert(BackstageUsers record) {
        backstageUsersMapper.insert(record);
        return record.getId();
    }

    @Override
    public long insertSelective(BackstageUsers record) {
        return 0;
    }

    @Override
    public BackstageUsers selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public long updateByPrimaryKeySelective(BackstageUsers record) {
        return 0;
    }

    @Override
    public long updateByPrimaryKey(BackstageUsers record) {
        return backstageUsersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BackstageUsers> findAll() {
        return backstageUsersMapper.findAll();
    }
}
