package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessagePlatformMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessagePlatform record);

    int insertSelective(MessagePlatform record);

    MessagePlatform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessagePlatform record);

    int updateByPrimaryKey(MessagePlatform record);
}