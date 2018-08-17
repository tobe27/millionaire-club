package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.MessagePlatform;

public interface MessagePlatformMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessagePlatform record);

    int insertSelective(MessagePlatform record);

    MessagePlatform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessagePlatform record);

    int updateByPrimaryKey(MessagePlatform record);
}