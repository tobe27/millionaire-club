package com.millionaire.millionairequartzmanager.lkjob.job;

import com.millionaire.millionairemanagerservice.module.MessagePlatform;

import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 平台消息定时发送任务
 * @date 2018/8/30 18:05
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class MessageSendJob {
    @Resource
    private MessagePlatformService messagePlatformService;
   /**
    * @Description 平台消息定时任务，将到时的定时消息状态 由 20等待发送 改为 10已发送
    **/
    public void sendMessage(){
        // 查询所有未发送的平台消息
        List<MessagePlatform> unsendMessage = messagePlatformService.listUnsendMessage();
        log.info("定时消息发送任务启动");
        // 循环未发送消息队列
        for(MessagePlatform message:unsendMessage){
            // 如果定时发送时间小于当前时间
            if(message.getTimingTime() <= System.currentTimeMillis()){
               message.setStatus((byte) 10);
               messagePlatformService.updateByPrimaryKeySelective(message);
                log.info("变更定时消息状态 id:{}",message.getId());
                log.info("变更定时消息状态:{}",message);
            }
        }
    }
}
