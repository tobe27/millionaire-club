package com.millionaire.millionaireuserservice.service.impl;

import com.millionaire.millionaireuserservice.dao.UserBankMapper;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.UserBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserBankServiceImpl implements UserBankService {
    @Resource
    private UserBankMapper userBankMapper;
    @Resource
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(UserBankServiceImpl.class);
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
        if(getRedis(uid)!=null){
            logger.info("在redis中获取用户银行卡信息！");
            return (List<UserBank>) getRedis(uid);
        }
        List<UserBank> userBanks = userBankMapper.selectByUID(uid);
        addRedis(uid,userBankMapper.selectByUID(uid));
        return userBanks;
    }
    public Object getRedis(Object key){
        return redisTemplate.opsForValue().get(key);
    }
    public void addRedis(Object key,Object value){
        redisTemplate.opsForValue().set(key,value,1000*60*5,TimeUnit.MILLISECONDS);
    }
}
