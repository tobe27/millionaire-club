package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/24 11:10
 */
@Service
public class InvestmentUserServiceImpl implements InvestmentUserService {

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

    /**
     * @param uid
     * @Description 通过用户id查询用户投资记录
     * @author Liu Kai
     */
    @Override
    public List<InvestmentUser> listInvestmentUserByUID(Long uid) {
        return investmentUserMapper.listInvestmentUserByUID(uid);
    }
}
