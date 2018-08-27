package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.MessageUser;

public interface MessageUserService {

    Long insert(MessageUser record);

    /**
     * 修改用户消息推送状态
     */
    int updateMessageUserCode(Long investmentUserId, Byte code);
}
