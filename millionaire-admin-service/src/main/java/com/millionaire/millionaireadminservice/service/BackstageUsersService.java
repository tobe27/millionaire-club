package com.millionaire.millionaireadminservice.service;

import com.millionaire.millionaireadminservice.module.BackstageUsers;

import java.util.List;

public interface BackstageUsersService {
    void deleteByPrimaryKey(Long id);

    long insert(BackstageUsers record);

    long insertSelective(BackstageUsers record);

    BackstageUsers selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(BackstageUsers record);

    long updateByPrimaryKey(BackstageUsers record);

    List<BackstageUsers> findAll();

    List<BackstageUsers> findByNameOrRole(BackstageUsers backstageUsers);

    BackstageUsers findByName(String name);

}
