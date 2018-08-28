package com.millionaire.millionaireserverweb.controller;

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
    }

    @Test
    public void updateInvestmentCredit() {
    }
}