package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.MessagePlatformQuery;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 平台消息
 * @date 2018/8/23 13:16
 */
@RestController
@RequestMapping("/a")
public class MessagePlatformController {

    private Logger logger = LoggerFactory.getLogger(MessagePlatformController.class);

    @Resource
    private MessagePlatformService messagePlatformService;

    /**
     * @Description 查看消息列表
     **/
    @GetMapping("/list/message-platform")
    public ResultBean listMessagePlatform(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                          MessagePlatformQuery query) {
        PageInfo<MessagePlatform> pageInfo = messagePlatformService.listMessageByPage(pageNum, pageSize, query);
        logger.info("查询消息列表:{}",query);
        return new ResultBean(0, "success", pageInfo);
    }

    /**
     * @Description 查看消息详细
     **/
    @GetMapping("/message-platform/{platformId}")
    public ResultBean selectMessage(@PathVariable("platformId") Long id) {
        MessagePlatform messagePlatform=messagePlatformService.selectByPrimaryKey(id);
        logger.info("查看消息详细:{}");
        return new ResultBean(0, "success", messagePlatform);
    }

/**
 * @Description 修改消息状态
 **/
@PutMapping("/message-platform-status/{platformId}")
public ResultBean updateMessageStatus(@PathVariable("platformId") Long id,
                                      @RequestParam("status")Byte status) {
    MessagePlatform messagePlatform=messagePlatformService.selectByPrimaryKey(id);
    if(messagePlatform == null){
        return new ResultBean(-1, "error no such id", id);
    }
     messagePlatform.setStatus(status);
    messagePlatformService.updateByPrimaryKeySelective(messagePlatform);
    logger.info("修改平台消息状态id:{},stauts:{}",id,status);
    return new ResultBean(0, "success", messagePlatform);
}


}
