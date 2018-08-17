package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Bank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
}