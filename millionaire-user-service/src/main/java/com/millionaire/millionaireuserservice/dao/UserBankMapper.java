package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.UserBank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);
}