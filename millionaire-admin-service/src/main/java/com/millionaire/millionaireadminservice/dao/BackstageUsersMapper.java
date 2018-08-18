package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.BackstageUsers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackstageUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BackstageUsers record);

    int insertSelective(BackstageUsers record);

    BackstageUsers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BackstageUsers record);

    int updateByPrimaryKey(BackstageUsers record);

    List<BackstageUsers> findAll();

    List<BackstageUsers> findByNameOrRole(BackstageUsers backstageUsers);

    BackstageUsers findByName(String name);
}