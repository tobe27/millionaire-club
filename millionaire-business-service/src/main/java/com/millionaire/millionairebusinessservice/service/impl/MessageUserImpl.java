package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.MessageUserMapper;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.service.MessageUserService;

import javax.annotation.Resource;

public class MessageUserImpl implements MessageUserService {

    @Resource
    private MessageUserMapper messageUserMapper;
    @Override
    public Long insert(MessageUser record) {
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        messageUserMapper.insert(record);
        return record.getId();
    }

}
