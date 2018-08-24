package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.service.ContentService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 运营内容管理
 * @date 2018/8/22 19:49
 */
@RestController
@RequestMapping("/a")
public class ContentController {
    private Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Resource
    private ContentService contentService;


    /**
     * @Description 查询运营内容详细
     **/
    @GetMapping("/content/{contentId}")
    public ResultBean getContent(@PathVariable("contentId") Long id) {
          Content content=contentService.selectByPrimaryKey(id);
          logger.info("查询运营内容id:{}",id);
        return new ResultBean(0, "success",content);
    }


    /**
     * @Description 新增运营内容
     **/
    @PostMapping("/content")
    public ResultBean insertContent(@Validated Content content) {
        contentService.insertSelective(content);
        Long id = content.getId();
        logger.info("新增内容id:{}", id);
        return new ResultBean(0, "success", id);
    }

    /**
     * @Description 修改内容
     **/
    @PutMapping("/content/{contentId}")
    public ResultBean updateContent(@PathVariable("contentId") Long id,
                                    @Validated Content content) {
        Content contentCheck = contentService.selectByPrimaryKey(id);
        if (contentCheck == null) {
            return new ResultBean(-1, "error no such id", id);
        }
        //轮播图封面不允许为空
        if (content.getType() == 10 && content.getCover() == null) {
            return new ResultBean(-1, "error banner cover not null", content.getType());
        }
        content.setId(id);
        contentService.updateByPrimaryKeySelective(content);
        logger.info("编辑内容id:{}", id);
        return new ResultBean(0, "success", id);
    }

}
