package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.BackstageUsers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BackstageUsersMapperTest {

    @Resource BackstageUsersMapper backstageUsersMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BackstageUsers backstageUsers = new BackstageUsers();
        backstageUsers.setName("GFGF");
        backstageUsers.setPassword("GFGF");
        backstageUsers.setSalt("GFGFGF");
        backstageUsers.setPhone("GFGF");
        backstageUsers.setRole("GFGF");
        backstageUsers.setGmtUpdate(System.currentTimeMillis());
        backstageUsers.setGmtCreate(5L);
        backstageUsers.setFounder("DSASD");
        backstageUsers.setModifier("FAFD");
        backstageUsersMapper.insert(backstageUsers);
        System.out.println(backstageUsers.getId());


    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}