package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.request.ClaimInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaimInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimInfo record);

    int insertSelective(ClaimInfo record);

    ClaimInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimInfo record);

    int updateByPrimaryKey(ClaimInfo record);

//    根据债券代码查询
    ClaimInfo selectByCode(String code);


    /**
     * @Description 精确查询债权信息
     * @param query 前台传递参数
   * @return  成功0 失败-1
     **/
    List<ClaimInfo> selectClaimByPage(ClaimInfoQuery query);
}