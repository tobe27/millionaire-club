package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.Roles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}