package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import javax.annotation.Resource;

public class InvestmentUserImpl implements InvestmentUserService {

    @Resource
    private InvestmentUserMapper investmentUserMapper;

    /**
     * 用户投资记录插入，发生在用户签署合同以后
     *
     * @param record
     * @return
     */
    @Override
    public Long insert(InvestmentUser record) {
        long time = System.currentTimeMillis();
        record.setGmtCreate(time);
        record.setGmtUpdate(time);
        return record.getUid();
    }

    @Override
    public Long selectTimeLimit() {
        return investmentUserMapper.selectTimeLimit();
    }
}
