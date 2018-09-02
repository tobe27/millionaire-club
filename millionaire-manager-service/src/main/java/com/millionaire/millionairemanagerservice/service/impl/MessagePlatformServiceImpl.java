package com.millionaire.millionairemanagerservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.dao.MessagePlatformMapper;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.request.MessagePlatformQuery;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/23 13:15
 */
@Service
public class MessagePlatformServiceImpl implements MessagePlatformService {
    @Resource
    private MessagePlatformMapper messagePlatformMapper;
//
    @Override
    public int deleteByPrimaryKey(Long id) {
        return messagePlatformMapper.deleteByPrimaryKey(id);
    }
//
//    @Override
//    public int insert(MessagePlatform record) {
//        return 0;
//    }
//
    @Override
    public Long insertSelective(MessagePlatform record) {
        long time= System.currentTimeMillis();
        record.setGmtUpdate(time);
        record.setGmtCreate(time);
        messagePlatformMapper.insertSelective(record);
        return record.getId();
    }
//
    @Override
    public MessagePlatform selectByPrimaryKey(Long id) {
        return messagePlatformMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MessagePlatform record) {
        record.setGmtUpdate(System.currentTimeMillis());
        return messagePlatformMapper.updateByPrimaryKeySelective(record);
    }

//    @Override
//    public int updateByPrimaryKey(MessagePlatform record) {
//        return 0;
//    }

    @Override
    public PageInfo<MessagePlatform> listMessageByPage(Integer pageNum, Integer pageSize, MessagePlatformQuery query) {
        PageHelper.startPage(pageNum,pageSize);
        List<MessagePlatform> messagePlatformList=messagePlatformMapper.selectByQuery(query);
        return new PageInfo<>(messagePlatformList);
    }


    @Override
    public List<MessagePlatform> listUnsendMessage() {
        return messagePlatformMapper.listUnsendMessage();
    }

    @Override
    public List<MessagePlatform> findBySendingCrowd(Byte sendingCrowd) {
        return messagePlatformMapper.findBySendingCrowd(sendingCrowd);
    }
}
