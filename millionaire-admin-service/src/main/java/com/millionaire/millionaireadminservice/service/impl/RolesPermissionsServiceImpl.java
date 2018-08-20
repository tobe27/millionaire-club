package com.millionaire.millionaireadminservice.service.impl;

import com.millionaire.millionaireadminservice.dao.RolesPermissionsMapper;
import com.millionaire.millionaireadminservice.module.RolesPermissions;
import com.millionaire.millionaireadminservice.service.RolesPermissionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolesPermissionsServiceImpl implements RolesPermissionsService {
    @Resource
    private RolesPermissionsMapper rolesPermissionsMapper;

    /**
     * 增加
     * 删除
     *通过角色id查询
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(Long id) {
        rolesPermissionsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public long insert(RolesPermissions record) {
        rolesPermissionsMapper.insert(record);
        return record.getId();
    }

    @Override
    public long insertSelective(RolesPermissions record) {
        return 0;
    }

    @Override
    public RolesPermissions selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public long updateByPrimaryKeySelective(RolesPermissions record) {
        return 0;
    }

    @Override
    public long updateByPrimaryKey(RolesPermissions record) {
        return 0;
    }

    @Override
    public List<RolesPermissions> findByRoleId(Long id) {
        return rolesPermissionsMapper.findByRoleId(id);
    }

}
