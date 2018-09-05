package com.millionaire.millionairemanagerservice.service;

import com.millionaire.millionairemanagerservice.module.MessageUserPlatform;

import java.util.List;

public interface MessageUserPlatformService {
    List<MessageUserPlatform> findAll();

    Long insert(MessageUserPlatform messageUserPlatform);

    Long updateById(MessageUserPlatform messageUserPlatform);

    MessageUserPlatform findById(Long id);

    void deleteById(Long id);

    Integer findByUidCount(Long uid);

    MessageUserPlatform findByMessagePlatform(MessageUserPlatform messageUserPlatform);

}
