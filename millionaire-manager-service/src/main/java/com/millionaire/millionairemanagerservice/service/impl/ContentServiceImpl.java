package com.millionaire.millionairemanagerservice.service.impl;

import com.millionaire.millionairemanagerservice.dao.ContentMapper;
import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.service.ContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/22 19:46
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentMapper contentMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Content record) {
        return 0;
    }
/**
 * @Description 封装创建时间 更新时间
 **/
    @Override
    public int insertSelective(Content record) {
        long time=System.currentTimeMillis();
        record.setGmtCreate(time);
        record.setGmtUpdate(time);
        return contentMapper.insertSelective(record);
    }

    @Override
    public Content selectByPrimaryKey(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /**
     * @Description 更新内容 封装更新时间
     **/
    @Override
    public int updateByPrimaryKeySelective(Content record) {
         record.setGmtUpdate(System.currentTimeMillis());
        return contentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Content record) {
        return 0;
    }



}
