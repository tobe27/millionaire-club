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
     * @param query 前台传递参数
     * @return 成功0 失败-1
     * @Description 精确查询债权信息
     **/
    List<ClaimInfo> selectClaimByPage(ClaimInfoQuery query);

    /**
     * @Description 选择未到期债权 用于定时任务排查债权到期时间 status != 2
     * status =2 表示已经过期
     **/
    List<ClaimInfo> selectClaimExpireCheck();

}