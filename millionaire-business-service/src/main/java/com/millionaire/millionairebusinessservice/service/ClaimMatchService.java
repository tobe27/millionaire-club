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

}
