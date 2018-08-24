package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
public interface BankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bank record);

    int insertSelective(Bank record);

//  @Select("select * from bank where id = #{id}")
    Bank selectByPrimaryKey(Long id);

    Bank selectByBankName(String bankName);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    List<Bank> selectAll();

    List<Bank> selectBankByPage(BankQuery query);
}