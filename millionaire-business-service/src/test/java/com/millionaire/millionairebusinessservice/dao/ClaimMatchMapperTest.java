package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClaimMatchMapperTest {

    @Resource ClaimMatchMapper claimMatchMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
//        ClaimMatch claimMatch = new ClaimMatch();
//        claimMatch.setClaimId(1L);
//        claimMatch.setInvestmentUserId(1L);
//        claimMatch.setCreditContractNumber("122545");
//        claimMatch.setStatus(1);
//        claimMatch.setGmtCreate(System.currentTimeMillis());
//        System.out.println(claimMatchMapper.insert(claimMatch));
//        System.out.println(claimMatch.getId());
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        ClaimMatch claimMatch = new ClaimMatch();
        claimMatch=claimMatchMapper.selectByPrimaryKey(1L);
        System.out.println(claimMatch);
    }


    @Test
    public void insert1() {
        ClaimMatch claimMatch = new ClaimMatch();
        claimMatch.setClaimId(1L);
        claimMatch.setInvestmentUserId(2L);
        claimMatch.setCreditContractNumber("dgfs");
        claimMatch.setGmtCreate(System.currentTimeMillis());
        claimMatch.setGmtUpdate(System.currentTimeMillis());
        claimMatch.setStatus((byte)1);
        claimMatchMapper.insert(claimMatch);
        System.out.println(claimMatch.getId());

    }


    @Test
    public void listClaimMatchByClaimID() {
        ClaimMatchQuery query=new ClaimMatchQuery();
        List<ClaimMatchDTO> claimMatchDTOS=claimMatchMapper.listClaimMatchByClaimID(query);
        System.out.println("claimMatchDTOS = " + claimMatchDTOS);
    }

    @Test
    public void countClaimMatch() {
        long count = claimMatchMapper.countClaimMatch();
        System.out.println("count = " + count);
    }
}