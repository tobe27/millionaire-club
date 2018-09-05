package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;

import java.util.List;

public interface MessageUserService {

    Long insert(MessageUser record);

    /**
     * 修改用户消息推送状态
     */
    int updateMessageUserCode(Long investmentUserId, Byte code);

    List<UserMessageDTO> findByUid(Long id);

    Integer findByLook();

    int updateByPrimaryKey(MessageUser record);
}
