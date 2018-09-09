package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageUser record);

    int insertSelective(MessageUser record);

    MessageUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageUser record);

    int updateByPrimaryKey(MessageUser record);

    /**
     * 修改用户消息推送状态
     */
    int updateMessageUserCode(@Param("investmentUserId") Long investmentUserId,
                              @Param("code") Byte code,
                              @Param("gmtUpdate") Long gmtUpdate);
    List<UserMessageDTO> findByUid(Long id);

    Integer findByLook(Long id);
    Integer findByMessagePlatformId(Long id);

    MessageUser findByUserMessage(MessageUser messageUser);

    int insetByUserMessage(MessageUser messageUser);

    int updateByUserMessage(MessageUser messageUser);

}