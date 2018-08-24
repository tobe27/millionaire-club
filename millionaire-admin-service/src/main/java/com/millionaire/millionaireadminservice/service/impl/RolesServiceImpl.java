package com.millionaire.millionaireadminservice.service.impl;

import com.millionaire.millionaireadminservice.dao.RolesMapper;
import com.millionaire.millionaireadminservice.module.Roles;
import com.millionaire.millionaireadminservice.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    @Resource
    private RolesMapper rolesMapper;

    /**
     * 增加
     * 删除
     * 修改
     * 查询所有
     *
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(Long id) {
        rolesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public long insert(Roles record) {
        rolesMapper.insert(record);
        return record.getId();
    }

    @Override
    public long insertSelective(Roles record) {
        return 0;
    }

    @Override
    public Roles selectByPrimaryKey(Long id) {
        return rolesMapper.selectByPrimaryKey(id);
    }

    @Override
    public long updateByPrimaryKeySelective(Roles record) {
        return 0;
    }

    @Override
    public long updateByPrimaryKey(Roles record) {

        return rolesMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Roles> findAll() {
        return rolesMapper.findAll();
    }

    @Override
    public Roles findByName(String name) {
        return rolesMapper.findByName(name);
    }
}
