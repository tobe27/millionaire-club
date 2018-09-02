package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClaimInfoMapperTest {

@Resource
ClaimInfoMapper claimInfoMapper;

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
    public void selectByCode() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
        ClaimInfo claimInfo=claimInfoMapper.selectByPrimaryKey(1L);
        System.out.println("claimInfo = " + claimInfo);
//        claimInfo.setExpirationDate();
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void listClaimInfo() {
    }

    @Test
    public void selectClaimExpireCheck() {

        List<ClaimInfo> list = claimInfoMapper.selectClaimExpireCheck();
        System.out.println("list = " + list);
    }
}