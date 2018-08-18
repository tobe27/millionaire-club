package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.BackstageUsers;
import com.millionaire.millionaireadminservice.module.Permissions;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BackstageUsersMapperTest {
    @Resource
    private BackstageUsersMapper backstageUsersMapper;
    @Resource
    private BackstageUsersService backstageUsersService;


    @Test
    public void insert() {
        BackstageUsers backstageUsers = new BackstageUsers();
        backstageUsers.setName("孙壮壮");
        backstageUsers.setPassword("GFGF");
        backstageUsers.setSalt("GFGFGF");
        backstageUsers.setPhone("GFGF");
        backstageUsers.setRole("GFGF");
        backstageUsers.setGmtUpdate(System.currentTimeMillis());
        backstageUsers.setGmtCreate(5L);
        backstageUsers.setFounder("DSASD");
        backstageUsers.setModifier("FAFD");
        backstageUsers.setId(5L);
        backstageUsersService.updateByPrimaryKey(backstageUsers);
        System.out.println(backstageUsers.getId());
    }

    @Test
    public void testFindAll(){
//        List<BackstageUsers> backstageUsers = backstageUsersMapper.findAll();
        List<BackstageUsers> backstageUsers = backstageUsersService.findAll();
        for (BackstageUsers backstageUsers1:backstageUsers) {
            System.out.println(backstageUsers1);
        }
    }
    @Test
    public void deleteByPrimaryKey() {
        backstageUsersService.deleteByPrimaryKey(1L);
    }

//    @Resource
//    private PermissionsMapper permissionsMapper;
//    @Test
//    public void testInsert(){
//        String [] list = {"业务管理","运营管理","统计信息","后台管理","用户管理","实名认证","产品管理","债权管理","债权匹配","内容管理","经理管理","意见反馈","版本管理","销量统计","账户管理","密码修改","角色管理","模块管理"};
//        for(int i=0;i<list.length;i++){
//            Permissions permissions = new Permissions();
//            permissions.setPermission(list[i]);
//            permissions.setGmtCreate(System.currentTimeMillis());
//            permissionsMapper.insert(permissions);
//            System.out.println(permissions.getId());
//        }
//    }
}