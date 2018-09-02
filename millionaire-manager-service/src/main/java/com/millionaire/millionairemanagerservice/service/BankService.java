package com.millionaire.millionairemanagerservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO  后台银行信息管理
 * @date 2018/8/21 15:39
 */
public interface BankService {

    int deleteByPrimaryKey(Long id);

    /**
     * @Description 添加银行信息，返回新增银行id 封装 createtime updatetime modifier
    * @param record
     * @return  新增银行id
     **/
    Long insert(Bank record);

//    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Long id);

//    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    PageInfo<Bank> selectBankByPage(Integer pageNum, Integer pageSize,
                                    BankQuery query);

    List<Bank> selectAll();
}
