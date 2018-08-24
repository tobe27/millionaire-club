package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.ProposalQuery;
import com.millionaire.millionairemanagerservice.transport.UserProposalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProposalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Proposal record);

    int insertSelective(Proposal record);

    Proposal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Proposal record);

    int updateByPrimaryKey(Proposal record);

    List<UserProposalDTO> selectByQuery(ProposalQuery query);

    UserProposalDTO selectUserProposalById(Long id);
}