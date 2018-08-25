package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.request.ContentQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    List<Content> listContentByQuery(ContentQuery query);

    List<Content> findByType(Byte type);

}