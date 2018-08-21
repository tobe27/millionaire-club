package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.UserBank;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface UserBankMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(UserBank record);

    Long insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(UserBank record);

    Long updateByPrimaryKey(UserBank record);

}