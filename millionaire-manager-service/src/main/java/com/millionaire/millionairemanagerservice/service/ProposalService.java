package com.millionaire.millionairemanagerservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.ProposalQuery;
import com.millionaire.millionairemanagerservice.transport.UserProposalDTO;


/**
 * @author Liu Kai
 * @Description: TODO 用户反馈
 * @date 2018/8/23 13:26
 */
public interface ProposalService {

    int deleteByPrimaryKey(Long id);
//
//    int insert(Proposal record);
//
//    int insertSelective(Proposal record);
//
//    Proposal selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(Proposal record);
//
//    int updateByPrimaryKey(Proposal record);

    PageInfo<UserProposalDTO> listProposalByPage(Integer pageNum, Integer pageSize, ProposalQuery query);

    UserProposalDTO selectUserProposalById(Long id);

    int insert(Proposal record);

}
