package com.millionaire.millionairemanagerservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.request.ContentQuery;

/**
 * @author Liu Kai
 * @Description: TODO 运营内容 新增 修改 查询 删除
 * @date 2018/8/22 19:41
 */
public interface ContentService {

    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    PageInfo<Content> selectContentByPage(Integer pageNum, Integer pageSize, ContentQuery query);

}
