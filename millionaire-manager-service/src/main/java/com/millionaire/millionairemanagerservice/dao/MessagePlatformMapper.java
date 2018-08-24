package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.MessagePlatformQuery;
import com.millionaire.millionairemanagerservice.request.ProposalQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessagePlatformMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessagePlatform record);

    int insertSelective(MessagePlatform record);

    MessagePlatform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessagePlatform record);

    int updateByPrimaryKey(MessagePlatform record);

    List<MessagePlatform> selectByQuery(MessagePlatformQuery query);
}