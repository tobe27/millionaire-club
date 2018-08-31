package com.millionaire.millionaireserverweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionairemanagerservice.request.MessagePlatformQuery;
import com.millionaire.millionairemanagerservice.service.MessagePlatformService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Liu Kai
 * @Description: TODO 后台平台消息
 * @date 2018/8/23 13:16
 */
@RestController
@RequestMapping("/a")
public class MessagePlatformController {

    private Logger logger = LoggerFactory.getLogger(MessagePlatformController.class);

    @Resource
    private MessagePlatformService messagePlatformService;

    /**
     * @Description 新增消息
     **/
    @PostMapping("/message-platform")
    public ResultBean insertMessagePlatform(@RequestBody @Validated MessagePlatform message) {
        //上线消息，选择定时发送时 定时发送时间需为未来时间
        //status=10 上线； type=20 定时发送
        System.out.println(message);
        if (message.getStatus() == 10 &&
                message.getType() == 20) {
            if (message.getTimingTime() == null) {
                return new ResultBean(-1, "error 上线消息定时发送时间为空", message);
            }
            Date date = new Date();
            if (date.before(new Date(message.getTimingTime()))) {
                message.setStatus((byte) 20);
                long id = messagePlatformService.insertSelective(message);
                logger.info("新增定时消息id:{},定时时间：{}", id, new Date(message.getTimingTime()));
                return new ResultBean(1, "success", id);
            }
            return new ResultBean(-1, "error 上线消息定时发送时间为过去时间", message);
        }
        long id = messagePlatformService.insertSelective(message);
        logger.info("新增消息id:{}", id);
        return new ResultBean(1, "success", id);

    }

    /**
     * @Description 查看消息列表
     **/
    @GetMapping("/list/message-platform")
    public ResultBean listMessagePlatform(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           MessagePlatformQuery query) {
        PageInfo<MessagePlatform> pageInfo = messagePlatformService.listMessageByPage(pageNum, pageSize, query);
        logger.info("查询消息列表:{}", query);
        return new ResultBean(1, "success", pageInfo);
    }

    /**
     * @Description 查看消息详细
     **/
    @GetMapping("/message-platform/{platformId}")
    public ResultBean selectMessage(@PathVariable("platformId") Long id) {
        MessagePlatform messagePlatform = messagePlatformService.selectByPrimaryKey(id);
        logger.info("查看消息详细:{}");
        return new ResultBean(1, "success", messagePlatform);
    }

    /**
     * @Description 修改消息状态
     **/
    @PutMapping("/message-platform-status/{platformId}")
    public ResultBean updateMessageStatus(@PathVariable("platformId") Long id,
                                          @RequestBody JSONObject jsonObject) {


        byte status = jsonObject.getByte("status");
        MessagePlatform messagePlatform = messagePlatformService.selectByPrimaryKey(id);
        if (messagePlatform == null) {
            return new ResultBean(-1, "error no such id", id);
        }
        //消息状态 0=草稿 10=上线/发送 20=等待发送(后台设置定时任务时专用参数)
        if (status != 0 && status != 10) {
            return new ResultBean(-1, "error status 0/10", status);
        }
        // 判断定时任务草稿时间是否过期
        //判断是否为定时任务
        if (messagePlatform.getType() == 20 ){
            //时间为空
           if (messagePlatform.getTimingTime() == null){
               return  new ResultBean(-1, "error 请设置定时任务时间再上线", messagePlatform);
           }
           //时间过期
            Date date= new Date();
           //现在时间在定时任务时间之后
           if( date.after(new Date(messagePlatform.getTimingTime()))){
               return new ResultBean(-1, "error 定时任务时间已经过期，请重新设置定时任务时间再上线", messagePlatform);
           }
            messagePlatform.setStatus(status);
            messagePlatformService.updateByPrimaryKeySelective(messagePlatform);
            logger.info("修改平台定时消息状态id:{},status:{}", id, status);
            return new ResultBean(1, "success", messagePlatform);
        }
        messagePlatform.setStatus(status);
        messagePlatformService.updateByPrimaryKeySelective(messagePlatform);
        logger.info("修改平台消息状态id:{},status:{}", id, status);
        return new ResultBean(1, "success", messagePlatform);
    }


    /**
     * @Description 删除平台消息
     **/
    @DeleteMapping("/message-platform/{platformId}")
    public ResultBean deleteMessagePlatform(@PathVariable("platformId") long id) {
        messagePlatformService.deleteByPrimaryKey(id);
        logger.info("删除平台消息id:{}", id);
        return new ResultBean(1, "success");
    }


}
