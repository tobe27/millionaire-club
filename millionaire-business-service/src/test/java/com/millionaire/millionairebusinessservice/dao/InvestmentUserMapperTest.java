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
}