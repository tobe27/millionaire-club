package com.millionaire.millionairemanagerservice.dao;

import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.ProposalQuery;
import com.millionaire.millionairemanagerservice.transport.UserProposalDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProposalMapperTest {
    @Resource
    private ProposalMapper mapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        Proposal p = new Proposal();
        p.setProposal("连表查询测试");
        p.setGmtCreate(System.currentTimeMillis());
        p.setGmtUpdate(System.currentTimeMillis());
       p.setUid(8L);
       mapper.insert(p);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByQuery() {
        ProposalQuery query = new ProposalQuery();

        List<UserProposalDTO> list = mapper.selectByQuery(query);
        System.out.println("list = " + list);
    }
}