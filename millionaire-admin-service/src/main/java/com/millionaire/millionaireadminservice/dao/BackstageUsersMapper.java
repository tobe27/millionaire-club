package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.BackstageUserDTO;
import com.millionaire.millionaireadminservice.module.BackstageUsers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackstageUsersMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(BackstageUsers record);

    Long insertSelective(BackstageUsers record);

    BackstageUsers selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BackstageUsers record);

    Long updatePassword(BackstageUsers backstageUsers);

    Long updateByPrimaryKey(BackstageUsers record);

    List<BackstageUsers> findAll();

    List<BackstageUserDTO> findByNameOrRole(BackstageUsers backstageUsers);

    BackstageUsers findByName(String name);

    BackstageUserDTO findById(Long id);

}