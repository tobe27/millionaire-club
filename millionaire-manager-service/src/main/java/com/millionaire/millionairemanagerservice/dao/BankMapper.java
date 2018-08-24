package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    List<Bank> selectAll();

    List<Bank> selectBankByPage(BankQuery query);
}