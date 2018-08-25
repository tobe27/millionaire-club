package com.millionaire.millionairemanagerservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.dao.ProposalMapper;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.ProposalQuery;
import com.millionaire.millionairemanagerservice.service.ProposalService;
import com.millionaire.millionairemanagerservice.transport.UserProposalDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 用户反馈
 * @date 2018/8/23 13:27
 */
@Service
public class ProposalServiceImpl implements ProposalService {

    @Resource
    private ProposalMapper proposalMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return proposalMapper.deleteByPrimaryKey(id);
    }
//
//    @Override
//    public int insert(Proposal record) {
//        return 0;
//    }
//
//    @Override
//    public int insertSelective(Proposal record) {
//        return 0;
//    }
//
//    @Override
//    public Proposal selectByPrimaryKey(Long id) {
//        return null;
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(Proposal record) {
//        return 0;
//    }
//
//    @Override
//    public int updateByPrimaryKey(Proposal record) {
//        return 0;
//    }


    @Override
    public PageInfo<UserProposalDTO> listProposalByPage(Integer pageNum, Integer pageSize, ProposalQuery query) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserProposalDTO> proposalList=proposalMapper.selectByQuery(query);
        return new PageInfo<>(proposalList);
    }


    @Override
    public UserProposalDTO selectUserProposalById(Long id) {
        return proposalMapper.selectUserProposalById(id);
    }
}
