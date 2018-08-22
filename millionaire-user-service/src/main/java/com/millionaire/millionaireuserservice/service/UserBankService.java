package com.millionaire.millionaireuserservice.service;

import com.millionaire.millionaireuserservice.module.UserBank;

public interface UserBankService {
    void deleteByPrimaryKey(Long id);

    Long insert(UserBank record);

    Long insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(UserBank record);

    Long updateByPrimaryKey(UserBank record);
}
