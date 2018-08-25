package com.millionaire.millionaireuserservice.service.impl;

import com.millionaire.millionaireuserservice.dao.UserBankMapper;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.UserBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBankServiceImpl implements UserBankService {
    @Resource
    private UserBankMapper userBankMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        userBankMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long insert(UserBank record) {
        userBankMapper.insert(record);
        return record.getId();
    }

    @Override
    public Long insertSelective(UserBank record) {
        return null;
    }

    @Override
    public UserBank selectByPrimaryKey(Long id) {
        return userBankMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long updateByPrimaryKeySelective(UserBank record) {
        return null;
    }

    @Override
    public Long updateByPrimaryKey(UserBank record) {
        userBankMapper.updateByPrimaryKey(record);
        return record.getId();
    }

    @Override
    public List<UserBank> selectByUID(Long uid) {
        return userBankMapper.selectByUID(uid);
    }


    /**
     * @param uid
     * @Description 根据uid 删除银行卡
     */
    @Override
    public void deleteByUID(Long uid) {
        userBankMapper.deleteByUID(uid);
    }
}
