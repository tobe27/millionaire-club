package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.MessageUserPlatform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MessageUserPlatformMapper {
    List<MessageUserPlatform> findAll();

    Long insert(MessageUserPlatform messageUserPlatform);

    Long updateById(MessageUserPlatform messageUserPlatform);

    MessageUserPlatform findById(Long id);

    void deleteById(Long id);

    Integer findByUidCount(Long uid);

    MessageUserPlatform findByMessagePlatform(MessageUserPlatform messageUserPlatform);
}
