package com.millionaire.millionaireuserservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReceptionUsersServiceImplTest {

    @Autowired
    ReceptionUsersService service;

    @Test
    public void selectProductByPage() {
        ReceptionUsersQuery query=new ReceptionUsersQuery();
        PageInfo<ReceptionUsers> pageInfo=service.selectProductByPage(query,1,1);
        System.out.println(pageInfo);
    }
}