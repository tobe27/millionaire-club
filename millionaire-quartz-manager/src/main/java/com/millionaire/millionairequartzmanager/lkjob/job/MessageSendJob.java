package com.millionaire.millionairequartzmanager.lkjob.job;

import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 平台消息定时发送任务
 * @date 2018/8/30 18:05
 */
@Component
@Configuration
@EnableScheduling
public class MessageSendJob {
//    @Resource
//    private MessagePlatformService messagePlatformService;
   /**
    * @Description 平台消息定时任务，将到时的定时消息状态 由 20等待发送 改为 10已发送
    **/
//    public void sendMessage(){
//        // 查询所有未发送的平台消息
//        List<MessagePlatform> unsendMessage = messagePlatformService.listUnsendMessage();
//        // 循环未发送消息队列
//        for(MessagePlatform message:unsendMessage){
//            // 如果定时发送时间小于当前时间
//            if(message.getTimingTime() <= System.currentTimeMillis()){
//
//            }
//
//        }


//    }




}
