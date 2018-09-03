package com.millionaire.millionaireserverweb.controller;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.request.TradingFlowQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import com.millionaire.millionairebusinessservice.transport.UserTradingFlowDTO;
import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.request.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
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
public class ReceptionUsersControllerTest {

    @Resource
    ReceptionUsersMapper usersMapper;

    @Autowired
    ReceptionUsersService usersService;

    @Resource
    private InvestmentUserService investmentUserService;

    @Resource
    private TradingFlowService flowService;
    
    @Test
    public void getListReceptionUsers() {
        ReceptionUsersQuery query=new ReceptionUsersQuery();
        PageInfo<ReceptionUsers> pageInfo =
                usersService.selectReceptionUserByPage(query, 10, 1);
        System.out.println("pageInfo = " + pageInfo);
    }

    @Test
    public void updateUserStatus() {
    }

    @Test
    public void selectUsers() {
        ReceptionUsers users=usersMapper.selectByPrimaryKey(8L);
        System.out.println("users = " + users);
//        ReceptionUsers users1=usersService.selectByPrimaryKey(8L);
//        System.out.println("users1 = " + users1);
    }

    @Test
    public void updateUserPhoneMangerNum() {
        ReceptionUsers users = usersService.selectByPrimaryKey(17L);
        users.setPhone(12345678890L);
        usersService.updateByPrimaryKeySelective(users);
        System.out.println("users = " + users);
        
    }

    @Test
    public void deleteBankCard() {
    }

    @Test
    public void listUserTradingFlow() {

        TradingFlowQuery query=new TradingFlowQuery();
        query.setUid(8L);
        PageInfo<UserTradingFlowDTO> pageInfo = flowService.selectTradingFlowBypage(1, 1, query);
        System.out.println("pageInfo = " + pageInfo);
    }

    @Test
    public void listUserInvestment() {
        InvestmentUserQuery query = new InvestmentUserQuery();
        query.setUid(1L);
//        query.setProductName("mmm1");
        List<InvestmentUserDTO> investmentUserDTOList = investmentUserService.listInvestmentUserByQuery(query);
        for (InvestmentUserDTO dto : investmentUserDTOList) {
            System.err.println(dto);
        }
    }

    @Test
    public void selectMatchedInvestmentUser() {
        List<InvestmentUser> list =investmentUserService.selectMatchedInvestmentUser(1L);
        System.out.println("list = " + list);
    }
}