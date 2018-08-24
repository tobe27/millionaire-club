package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.MessageUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageUser record);

    int insertSelective(MessageUser record);

    MessageUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageUser record);

    int updateByPrimaryKey(MessageUser record);
}