package com.millionaire.millionairemanagerservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.request.MessagePlatformQuery;
import com.millionaire.millionairemanagerservice.transport.MessagePlatformDTO;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 平台消息 新增 编辑 删除 查看
 * @date 2018/8/23 13:07
 */
public interface MessagePlatformService {
    int deleteByPrimaryKey(Long id);
//
//    int insert(MessagePlatform record);
//
    Long insertSelective(MessagePlatform record);
//
    MessagePlatform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessagePlatform record);

//    int updateByPrimaryKey(MessagePlatform record);

    PageInfo<MessagePlatform> listMessageByPage(Integer pageNum, Integer pageSize, MessagePlatformQuery query);


    List<MessagePlatform> listUnsendMessage();

    List<MessagePlatformDTO> findBySendingCrowd(Byte sendingCrowd);

    Integer findBySendingCrowdCount(Byte sendingCrowd);


}
