package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.Roles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolesMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(Roles record);

    Long insertSelective(Roles record);

    Roles selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(Roles record);

    Long updateByPrimaryKey(Roles record);

    List<Roles> findAll();

    Roles findByName(String name);
}