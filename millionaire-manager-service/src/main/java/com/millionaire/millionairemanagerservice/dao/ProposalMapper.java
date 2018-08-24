package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Proposal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProposalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Proposal record);

    int insertSelective(Proposal record);

    Proposal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Proposal record);

    int updateByPrimaryKey(Proposal record);
}