package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionaireadminservice.module.BackstageUsers;
import com.millionaire.millionaireadminservice.module.Roles;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionaireadminservice.service.RolesService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SunControllerTest {

    @Resource
    private BackstageUsersService backstageUsersService;

    @Test
    public void backstageLogin() {
    }

    @Test
    public void subLogin() {
    }

    @Test
    public void getUsers() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void getBackstageUserById() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void getRoles() {
    }

    @Test
    public void getRolesAll() {
    }

    @Test
    public void deleteRole() {
    }

    @Test
    public void insertRole() {
    }

    @Test
    public void updateRole() {
    }

    @Test
    public void selectRolePermission() {
    }

    @Test
    public void getPermission() {
    }

    @Test
    public void insertBackUser() {
        BackstageUsers users = new BackstageUsers();
        String password = "123456";
        String salt = String.valueOf(new Random().nextInt(899999) + 100000);
        String md5HashPassword = new Md5Hash(password, salt, 2).toString();
        users.setName("admin");
        users.setPassword(md5HashPassword);
        users.setSalt(salt);
        users.setPhone("13700000000");
        users.setRoleId(1L);
        users.setGmtUpdate(System.currentTimeMillis());
        users.setGmtCreate(System.currentTimeMillis());
        users.setFounder("admin");
        users.setModifier("admin");
        backstageUsersService.insert(users);
    }

    @Resource
    private RolesService rolesService;
    @Test
    public void insertRoles() {
        Roles roles = new Roles();
        roles.setRoleName("admin");
        roles.setGmtCreate(System.currentTimeMillis());
        roles.setGmtUpdate(System.currentTimeMillis());
        roles.setFounder("admin");
        roles.setModifier("admin");
rolesService.insert(roles);

    }

    @Test
    public void rolesTest() {

    }
}