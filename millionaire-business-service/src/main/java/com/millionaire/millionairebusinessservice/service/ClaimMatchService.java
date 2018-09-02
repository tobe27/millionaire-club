package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 债权匹配信息查询
 * @date 2018/8/27 14:59
 */
public interface ClaimMatchService {

    /**
     * @Description 根据债权信息id动态查询债权匹配信息
     **/
    List<ClaimMatchDTO> listClaimMatchByClaimID(ClaimMatchQuery query);

    /**
     * @Description 根据债权id给出推荐匹配的用户投资列表
     **/
    List<InvestmentUser> listRecommendInvestmentUser(long claimID);

    /**
     * @Description 新增债权匹配信息
     **/

    Long insertClaimMatch(ClaimMatch claimMatch);


    /**
     * @Description 获取当前存储的债权协议编号总数
     **/
    long countClaimMatch();


    /**
     * @Description 根据债权id 查找有效的债权匹配信息
     * status = 1 表示该债权可用
     **/
    List<ClaimMatch> listEffectClaimMatchByClaimID(long claimID);

    /**
     * @Description 动态更新债权匹配信息 返回债权信息id
     **/
    long updateByPrimaryKeySelective(ClaimMatch record);


    /**
     * @Description 根据用户投资id查找当前有效的债权匹配信息
     * 一份用户投资同一时间只有一份有效的债权匹配信息
     * 查询条件
     * investmentUserID
     * status=1 该债权匹配信息有效
     * **/
    ClaimMatch selectEffectByInvestmentUID(long investmentUserID);
}
