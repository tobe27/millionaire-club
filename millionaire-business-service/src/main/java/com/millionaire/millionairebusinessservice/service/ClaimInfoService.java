package com.millionaire.millionairebusinessservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.request.ClaimInfoQuery;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 债权信息查看 修改 新增
 * @date 2018/8/21 9:45
 */
public interface ClaimInfoService {

    /**
     * @param record 债券信息
     * @return 成功0 失败-1
     * @Description 新增债券信息 动态插入 封装 create update time
     **/
    Long insert(ClaimInfo record);

    /**
     * @param code 债权代码
     * @return 成功0 失败-1
     * @Description 根据债券代码查询
     **/
    ClaimInfo selectByCode(String code);


    PageInfo<ClaimInfo> selectClaimBypage(Integer pageSize,Integer pageNum,ClaimInfoQuery query);

    int deleteByPrimaryKey(Long id);

    Long insertSelective(ClaimInfo record);

    ClaimInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimInfo record);

//    int updateByPrimaryKey(ClaimInfo record);

    /**
     * @Description 选择未到期债权 用于定时任务排查债权到期时间 status != 2
     *  status =2 表示已经过期
     **/
    List<ClaimInfo> selectClaimExpireCheck();


}
