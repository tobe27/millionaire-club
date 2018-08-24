package com.millionaire.millionairemanagerservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;

/**
 * @author Liu Kai
 * @Description: TODO  后台银行信息管理
 * @date 2018/8/21 15:39
 */
public interface BankService {

    int deleteByPrimaryKey(Long id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    PageInfo<Bank> selectBankByPage(Integer pageNum, Integer pageSize,
                                    BankQuery query);
}
