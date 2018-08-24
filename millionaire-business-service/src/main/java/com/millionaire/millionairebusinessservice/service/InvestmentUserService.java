package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;

public interface InvestmentUserService {

    Long insert(InvestmentUser record);

    /**
     * 查询最新记录的用户id
     */
    Long selectTimeLimit();
}
