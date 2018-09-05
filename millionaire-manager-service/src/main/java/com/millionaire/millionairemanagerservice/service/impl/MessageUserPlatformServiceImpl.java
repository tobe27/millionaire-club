package com.millionaire.millionairemanagerservice.service.impl;

import com.millionaire.millionairemanagerservice.dao.MessageUserPlatformMapper;
import com.millionaire.millionairemanagerservice.module.MessageUserPlatform;
import com.millionaire.millionairemanagerservice.service.MessageUserPlatformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageUserPlatformServiceImpl implements MessageUserPlatformService {
    @Resource
    private MessageUserPlatformMapper messageUserPlatformMapper;
    @Override
    public List<MessageUserPlatform> findAll() {
        return messageUserPlatformMapper.findAll();
    }

    @Override
    public Long insert(MessageUserPlatform messageUserPlatform) {
        return messageUserPlatformMapper.insert(messageUserPlatform);
    }

    @Override
    public Long updateById(MessageUserPlatform messageUserPlatform) {
        return messageUserPlatformMapper.updateById(messageUserPlatform);
    }

    @Override
    public MessageUserPlatform findById(Long id) {
        return messageUserPlatformMapper.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        messageUserPlatformMapper.deleteById(id);
    }

    @Override
    public Integer findByUidCount(Long uid) {
        return messageUserPlatformMapper.findByUidCount(uid);
    }

    @Override
    public MessageUserPlatform findByMessagePlatform(MessageUserPlatform messageUserPlatform) {
        return messageUserPlatformMapper.findByMessagePlatform(messageUserPlatform);
    }
}
