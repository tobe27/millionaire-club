package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaimMatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimMatch record);

    int insertSelective(ClaimMatch record);

    ClaimMatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimMatch record);

    int updateByPrimaryKey(ClaimMatch record);


    /**
     * @Description 根据债权信息id动态查询债权匹配信息
     **/
    List<ClaimMatchDTO> listClaimMatchByClaimID(ClaimMatchQuery query);

}