package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;

/**
 * @author Liu Kai
 * @Description: TODO 债权信息查看 修改 新增
 * @date 2018/8/21 9:45
 */
public interface ClaimInfoService {

/**
 * @Description 新增债券信息 动态插入 封装 create update time
 * @param record 债券信息
 * @return  成功0 失败-1
 **/
Long insert(ClaimInfo record);


/**
 * @Description  根据债券代码查询
 * @param code 债权代码
 * @return  成功0 失败-1
 **/
ClaimInfo selectByCode(String code);
}
