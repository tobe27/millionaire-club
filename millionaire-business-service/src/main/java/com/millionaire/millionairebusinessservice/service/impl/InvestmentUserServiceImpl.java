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


    @Override
    public Long insert(InvestmentUser record) {
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        investmentUserMapper.insert(record);
        return record.getId();
    }

    @Override
    public Long selectTimeLimit() {
        return null;
    }


    /**
     * @param uid
     * @Description 通过用户id查询用户投资记录
     * @author Liu Kai
     */
    @Override
    public List<InvestmentUser> listInvestmentUserByUID(Long uid) {
        return null;
    }
}
