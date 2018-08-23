package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


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
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}