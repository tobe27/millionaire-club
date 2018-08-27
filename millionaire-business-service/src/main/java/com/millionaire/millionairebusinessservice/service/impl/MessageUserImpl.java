package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.MessageUserMapper;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageUserImpl implements MessageUserService {

    private Logger logger = LoggerFactory.getLogger(MessageUserImpl.class);

    @Resource
    private MessageUserMapper messageUserMapper;
    @Override
    public Long insert(MessageUser record) {
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        logger.info("用户消息记录插入："+record);
        messageUserMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateMessageUserCode(Long investmentUserId, Byte code) {
        return messageUserMapper.updateMessageUserCode(investmentUserId,code,System.currentTimeMillis());
    }

}
