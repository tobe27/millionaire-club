package com.millionaire.millionaireadminservice.service.impl;

import com.millionaire.millionaireadminservice.dao.BackstageUsersMapper;
import com.millionaire.millionaireadminservice.module.BackstageUserDTO;
import com.millionaire.millionaireadminservice.module.BackstageUsers;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BackstageUsersServiceImpl implements BackstageUsersService {
    @Resource
    private BackstageUsersMapper backstageUsersMapper;

    /**
     * 增加
     * 删除
     * 查询所有
     * 更新
     * 条件查询
     * 通过名称查询
     *
     * @param id
     */

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
        return backstageUsersMapper.selectByPrimaryKey(id);
    }

    @Override
    public long updateByPrimaryKeySelective(BackstageUsers record) {
        return 0;
    }

    @Override
    public Long updatePassword(BackstageUsers backstageUsers) {
        return backstageUsersMapper.updatePassword(backstageUsers);
    }

    @Override
    public long updateByPrimaryKey(BackstageUsers record) {
        return backstageUsersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BackstageUsers> findAll() {
        return backstageUsersMapper.findAll();
    }

    @Override
    public List<BackstageUserDTO> findByNameOrRole(BackstageUsers backstageUsers) {
        return backstageUsersMapper.findByNameOrRole(backstageUsers);
    }

    @Override
    public BackstageUsers findByName(String name) {
        return backstageUsersMapper.findByName(name);
    }

    @Override
    public BackstageUserDTO findById(Long id) {
        return backstageUsersMapper.findById(id);
    }
}
