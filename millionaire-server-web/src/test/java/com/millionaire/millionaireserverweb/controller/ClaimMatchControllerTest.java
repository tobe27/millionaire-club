package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.dao.ClaimMatchMapper;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClaimMatchControllerTest {
@Resource
private ClaimMatchService claimMatchService;

    @Test
    public void listClaimMatch() {
    }

    @Test
    public void listRecommendedMatch() {
        List<InvestmentUser> investmentUserList = claimMatchService.listRecommendInvestmentUser(10);
        System.out.println("investmentUserList = " + investmentUserList);
    }

    @Test
    public void updateInvestmentCredit() {
    }
 
@Resource
private ClaimMatchMapper claimMatchMapper;
    @Test
    public void listEffectClaimMatchByClaimID() {
        List<ClaimMatch> effectClaimMatchList =
                claimMatchMapper.listEffectClaimMatchByClaimID(1L);
        System.out.println("effectClaimMatchList = " + effectClaimMatchList);

//        ClaimMatch claimMatch= claimMatchMapper.selectByPrimaryKey(7L);
//        System.out.println("claimMatch = " + claimMatch);

    }
}