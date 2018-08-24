package com.millionaire.millionairemanagerservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.dao.BankMapper;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/21 15:40
 */

@Service
public class BankServiceImpl implements BankService {
private  Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Resource
   private BankMapper bankMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Bank record) {
        return 0;
    }

    @Override
    public int insertSelective(Bank record) {
        return 0;
    }

    @Override
    public Bank selectByPrimaryKey(Long id) {
        System.out.println(id);

        return bankMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Bank record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Bank record) {
        return 0;
    }


    @Override
    public PageInfo<Bank> selectBankByPage(Integer pageNum, Integer pageSize, BankQuery query) {
        logger.info("service message get:{},{},{}",pageNum,pageSize,query);
        System.out.println(pageNum);
        System.out.println(pageSize);
        System.out.println(query);
        PageHelper.startPage(pageNum,pageSize);
        List<Bank> bankList=bankMapper.selectBankByPage(query);
        return new PageInfo<>(bankList);
    }
}
