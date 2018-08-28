package com.millionaire.millionaireuserservice.service;

import com.millionaire.millionaireuserservice.module.UserBank;

import java.util.List;

public interface UserBankService {
    void deleteByPrimaryKey(Long id);

    Long insert(UserBank record);

    Long insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(UserBank record);

    Long updateByPrimaryKey(UserBank record);

    List<UserBank> selectByUID(Long uid);

    /**
     * @Description 根据uid 删除银行卡
     **/
    void deleteByUID(Long uid);

    List<UserBank> findById(Long id);
}
