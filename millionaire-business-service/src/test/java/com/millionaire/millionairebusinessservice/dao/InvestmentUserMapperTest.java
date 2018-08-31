package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.ZoneId;
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
        InvestmentUser user=userMapper.selectByPrimaryKey(12L);
        System.out.println("user = " + user);
        System.out.println(user.getClaimId());
        if(user.getClaimId() == null){
            System.out.println("null"+user.getClaimId());
        }
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
//        query.setUid(8L);
//        query.setProductName("mmm");
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

    @Test
    public void updateInvestmentUserIdStatus() {
        int a = userMapper.updateInvestmentUserIdStatus(6L, (byte) 10, System.currentTimeMillis());
        System.out.println(a);
    }

    @Test
    public void updateLendingContractNumber() {
        int a = userMapper.updateLendingContractNumber(6L, "kdfksgfddgfs", System.currentTimeMillis());
        System.out.println(a);
    }

    @Test
    public void selectByLendingContractNumber() {
        InvestmentUser investmentUser=userMapper.selectByLendingContractNumber("1");
        System.out.println("investmentUser = " + investmentUser);
    }

    @Test
    public void selectRenewalInvestmentById() {
        System.out.println(userMapper.selectRenewalInvestmentById(19L));
    }

    @Test
    public void listRenewalInvestments() {
        int investmentEnd = 4;
//        查询到期日期小于续投参数的用户投资，当天的不包括在内
        LocalDate now = LocalDate.now();
//        续投产品的戒指日期
        LocalDate end = now.minusDays(investmentEnd);

//        转成时间戳
        long nowTime = now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTime = end.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(userMapper.listRenewalInvestments(endTime,nowTime));
    }

    @Test
    public void selectContractResponse() {
        System.out.println(userMapper.selectContractResponse(19L));
    }
}