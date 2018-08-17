package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceptionUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceptionUsers record);

    int insertSelective(ReceptionUsers record);

    ReceptionUsers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceptionUsers record);

    int updateByPrimaryKey(ReceptionUsers record);
}