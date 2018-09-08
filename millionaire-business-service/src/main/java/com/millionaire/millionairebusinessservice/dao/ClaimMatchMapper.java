package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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


    /**
     * @Description 获取当前存储的债权协议编号总数
     **/
    long countClaimMatch();

    /**
     * @Description 根据债权id 查找有效的债权匹配信息
     * status = 1 表示该债权可用
     **/
    List<ClaimMatch> listEffectClaimMatchByClaimID(Long claimId);

    /**
     * @Description 根据用户投资id查找当前有效的债权匹配信息
     * 一份用户投资同一时间只有一份有效的债权匹配信息
     * 查询条件
     * investmentUserID
     * status=1 该债权匹配信息有效
     * **/
      ClaimMatch selectEffectByInvestmentUID(long investmentUserID);


    /**
     *@author qiaobao
     *@datetime  2018/9/6 6:08
     *@decribe 修改债权匹配信息
     */

    int updateStatus(@Param("id") Long id,
                     @Param("status") Byte status,
                     @Param("gmtUpdate") Long gmtUpdate);

}