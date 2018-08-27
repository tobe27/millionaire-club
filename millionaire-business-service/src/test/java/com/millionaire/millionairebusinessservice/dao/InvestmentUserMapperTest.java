package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvestmentUserMapperTest {
@Resource
private InvestmentUserMapper userMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
        InvestmentUser investmentUser=new InvestmentUser();
        investmentUser.setProductId(2L);
        investmentUser.setUid(8L);
        investmentUser.setValueDateStart(2342423423L);
        investmentUser.setValueDateEnd(69687987979L);
        investmentUser.setLendingContractNumber("lendingNUm");
        investmentUser.setContractSign("contractsign");
        investmentUser.setInvestmentAmount(10000);
        investmentUser.setGmtCreate(System.currentTimeMillis());
        investmentUser.setGmtUpdate(System.currentTimeMillis());

    }

    @Test
    public void selectByPrimaryKey() {
        InvestmentUser user=userMapper.selectByPrimaryKey(1L);
        System.out.println("user = " + user);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void listInvestmentUserByUID() {
    }

    @Test
    public void listInvestmentUserByQuery() {
        InvestmentUserQuery query=new InvestmentUserQuery();
        query.setUid(8L);
        query.setProductName("mmm");
        List<InvestmentUserDTO> list=userMapper.listInvestmentUserByQuery(query);
        System.out.println("list = " + list);
    }

    @Test
    public void selectUsableInvestment() {
        List<InvestmentUser> list=userMapper.selectUsableInvestment();
        System.out.println("list = " + list);
    }


    @Test
    public void selectMatchedUID() {
        List a= userMapper.selectMatchedUID(1);
        System.out.println("a = " + a);
        
    }
}