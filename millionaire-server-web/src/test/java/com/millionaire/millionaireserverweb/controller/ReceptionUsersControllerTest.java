package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.dao.UserBankMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
import com.millionaire.millionaireuserservice.transport.ReceptionUsersDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReceptionUsersControllerTest {


    @Resource
    private InvestmentProductService investmentProductService;

    @Resource
    private InvestmentUserService investmentUserService;

    @Resource
    private ReceptionUsersMapper receptionUsersMapper;

    @Resource
    private ReceptionUsersService receptionUsersService;

    @Resource
    private UserBankMapper userBankMapper;

    @Test
    public void insert() {
        ReceptionUsersDTO receptionUsersDTO = receptionUsersMapper.selectDTOById(1L);
        System.out.println("receptionUsersDTO = " + receptionUsersDTO);

    }


    /**
     * TODO  用户实名认证申请
     * by Liu Kai 2018/9/15 13:55
     **/
    @Test
    public void updateAuth() {
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(3L);
        receptionUsers.setIdName("大黑");
        receptionUsers.setIdNumber("123456789098765432");
        receptionUsers.setIdFront("http://majorjoe.oss-cn-beijing.aliyuncs.com/0e02a0a6-e67f-4746-8395-91e79cffe13d.jpg");
        receptionUsers.setIdBack("http://majorjoe.oss-cn-beijing.aliyuncs.com/0e02a0a6-e67f-4746-8395-91e79cffe13d.jpg");
        receptionUsers.setIdAuthentication((byte) 20);
        receptionUsersService.updateByPrimaryKeySelective(receptionUsers);
    }

    /**
     * TODO 用户申请添加银行卡
     * by Liu Kai 2018/9/15 14:12
     **/
    @Test
    public void insertUserBank() {
        for (long i = 0; i < 3; i++) {
            UserBank userBank = new UserBank();
            userBank.setCity("上海");
            userBank.setBankName("工商银行");
            userBank.setCardNumber("6222081208006091828");
            userBank.setCardType("储蓄卡");
            userBank.setBankPhone("13705795506");
            userBank.setUid(i);
            userBank.setGmtCreate(System.currentTimeMillis());
            userBank.setGmtUpdate(System.currentTimeMillis());
            userBankMapper.insertSelective(userBank);
        }
    }
}