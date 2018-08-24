package com.millionaire.millionairemanagerservice.service.impl;

import com.millionaire.millionairemanagerservice.service.ProposalService;
import com.millionaire.millionairemanagerservice.transport.UserProposalDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProposalServiceImplTest {

    @Resource
    private ProposalService service;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
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
    public void listProposalByPage() {
    }

    @Test
    public void selectUserProposalById() {
        UserProposalDTO userProposalDTO=service.selectUserProposalById(1L);
        System.out.println("userProposalDTO = " + userProposalDTO);
    }
}