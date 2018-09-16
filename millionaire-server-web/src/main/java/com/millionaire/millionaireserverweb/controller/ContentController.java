package com.millionaire.millionaireserverweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.request.ContentQuery;
import com.millionaire.millionairemanagerservice.service.ContentService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * @Description 查看内容列表
     **/
    @GetMapping("/list/content")
    public ResultBean listContent(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  ContentQuery query) {
        PageInfo<Content> pageInfo = contentService.selectContentByPage(pageNum, pageSize, query);
        logger.info("查询运营内容列表:{}", query);
        return new ResultBean(1, "success", pageInfo);
    }

    /**
     * @Description 查询运营内容详细
     **/
    @GetMapping("/content-info/{contentId}")
    public ResultBean getContent(@PathVariable("contentId") Long id) {
        Content content = contentService.selectByPrimaryKey(id);
        logger.info("查询运营内容id:{}", id);
        return new ResultBean(1, "success", content);
    }


    /**
     * @Description 新增运营内容
     * 0903bug 已上架的banner数量不超过6个
     **/
    @PostMapping("/content")
    public ResultBean insertContent(@RequestBody @Validated Content content) {
        //轮播图封面不允许为空
        if (content.getType() == 20 && content.getCover() == null) {
            return new ResultBean(-1, "error banner cover is null", content);
        }
        //0903bug 已上架的banner数量不超过6个
        if(content.getType() ==10 && content.getState() == 10){
            List<Content> contentList =contentService.listCoverShelf();
            if(contentList.size() >= 6){
                return new ResultBean(-1,"error to much effective banner");
            }
        }
        //添加编辑者信息
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        content.setEditors(userName);
        contentService.insertSelective(content);
        Long id = content.getId();
        logger.info("新增内容id:{}", id);
        return new ResultBean(1, "success", id);
    }

    /**
     * @Description 修改内容
     **/
    @PutMapping("/content/{contentId}")
    public ResultBean updateContent(@PathVariable("contentId") Long id,
                                   @RequestBody @Validated Content content) {
        Content contentCheck = contentService.selectByPrimaryKey(id);
        if (contentCheck == null) {
            return new ResultBean(-1, "error no such id", id);
        }
        //轮播图封面不允许为空
        if (content.getType() == 20 && content.getCover() == null) {
            return new ResultBean(-1, "error banner cover is null", content);
        }
        // 内容状态必须为 10 20 30
        if (content.getType() != 10 && content.getType() != 20 && content.getType() != 30){
            return new ResultBean(-1, "error type", content);
        }
        // 状态必须为10 20
         if (content.getState() != 10 && content.getState() != 20){
             return new ResultBean(-1, "error state", content);
         }
         //封装内容id
        content.setId(id);
        //封装内容创建时间
        content.setGmtCreate(contentCheck.getGmtCreate());
        //添加编辑者信息
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        content.setEditors(userName);
        contentService.updateByPrimaryKeySelective(content);
        logger.info("编辑内容id:{}", id);
        return new ResultBean(1, "success", id);
    }

    /**
     * @Description 修改运营内容状态
     * @RequestParam("status") byte status
     **/
    @PutMapping("/content-status/{contentId}")
    public ResultBean updateStatus(@PathVariable("contentId") Long id,
                                   @RequestBody JSONObject jsonObject) {

        Byte status = jsonObject.getByte("status");
        Content content = contentService.selectByPrimaryKey(id);
        if (content == null) {
            return new ResultBean(-1, "error id", id);
        }
        if(status == null){
            return new ResultBean(-1, "error statusNum", status);
        }
        if ( status != 10 && status != 20) {
            return new ResultBean(-1, "error statusNum", status);
        }
        content.setState(status);
        //添加编辑者信息
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        content.setEditors(userName);
        contentService.updateByPrimaryKeySelective(content);
        logger.info("更新运营内容状态id：{},status:{}", id, status);
        return new ResultBean(1, "success", content);
    }

    @DeleteMapping("/content/{contentId}")
    public ResultBean deleteContent(@PathVariable("contentId") long id) {
        logger.info("删除内容id:{}", id);
        contentService.deleteByPrimaryKey(id);
        return new ResultBean(1, "success", id);
    }

}
