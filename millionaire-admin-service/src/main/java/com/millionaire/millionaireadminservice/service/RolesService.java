package com.millionaire.millionaireadminservice.service;

import com.millionaire.millionaireadminservice.module.Roles;

import java.util.List;

public interface RolesService {
    void deleteByPrimaryKey(Long id);

    long insert(Roles record);

    long insertSelective(Roles record);

    Roles selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(Roles record);

    long updateByPrimaryKey(Roles record);

    List<Roles> findAll();

    Roles findByName(String name);
}
