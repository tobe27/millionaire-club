package com.millionaire.millionaireadminservice.dao;

import com.millionaire.millionaireadminservice.module.BackstageUsers;

import com.millionaire.millionaireadminservice.module.Roles;
import com.millionaire.millionaireadminservice.module.RolesPermissions;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionaireadminservice.service.PermissionsService;
import com.millionaire.millionaireadminservice.service.RolesPermissionsService;
import com.millionaire.millionaireadminservice.service.RolesService;
import com.millionaire.millionaireadminservice.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
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
    private BackstageUsersService backstageUsersService;

    @Test
    public void insert() {
        BackstageUsers backstageUsers = new BackstageUsers();
        backstageUsers.setName("路人甲");
        backstageUsers.setPassword("GFGF");
        backstageUsers.setSalt("GFGFGF");
        backstageUsers.setPhone("GFGF");
        backstageUsers.setRoleId(2L);
        backstageUsers.setGmtUpdate(System.currentTimeMillis());
        backstageUsers.setGmtCreate(5L);
        backstageUsers.setFounder("DSASD");
        backstageUsers.setModifier("FAFD");
        backstageUsersService.insert(backstageUsers);
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
    @Resource
    private BackstageUsersMapper backstageUsersMapper;
    @Test
    public void testI(){
        BackstageUsers backstageUsers = new BackstageUsers();
        backstageUsers.setName("孙");
        List<BackstageUsers> list = backstageUsersService.findByNameOrRole(backstageUsers);
        for (BackstageUsers backstageUsers1:list) {
            System.out.println(backstageUsers1);
        }
    }

    @Test
    public void TestS(){
        BackstageUsers backstageUsers = backstageUsersService.findByName("孙壮壮");
        System.out.println(backstageUsers);
    }
    @Test
    public void deleteByPrimaryKey() {
        backstageUsersService.deleteByPrimaryKey(3L);
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

@Resource
PermissionsService permissionsService;
    @Test
    public void testPermission(){
        System.out.println(permissionsService.selectByPrimaryKey(1L));
    }


    @Resource
    private RolesMapper rolesMapper;
    @Resource
    private RolesService rolesService;
    @Test
    public void testRole(){
        Roles roles = new Roles();
        roles.setRoleName("普通用户");
        roles.setGmtCreate(System.currentTimeMillis());
        roles.setFounder("sunzz");
        roles.setId(3L);
        rolesService.updateByPrimaryKey(roles);
        System.out.println(roles.getId());
    }
    @Test
    public void testAll(){
        List<Roles> roles = rolesService.findAll();
        for (Roles role:roles) {
            System.out.println(role);
        }
    }
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    @Test
    public void testR(){
        for(long i=1;i<10;i++){
            RolesPermissions rolesPermissions = new RolesPermissions();
            rolesPermissions.setRoleId(1L);
            rolesPermissions.setPermissionId(i);
            rolesPermissions.setGmtCreate(System.currentTimeMillis());
            rolesPermissionsService.insert(rolesPermissions);
            System.out.println(rolesPermissions.getId());
        }
    }
    @Test
    public void testD(){
        rolesPermissionsService.deleteByPrimaryKey(9L);
    }


    @Resource
    private ShiroRealm shiroRealm;
    @Test
    public void testMyRealm(){

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(shiroRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("小白","123456");
        subject.login(token);
        System.out.println("是否认证："+subject.isAuthenticated());
//        subject.checkRole("管理员");
//        subject.checkPermission("后台管理");
    }
    @Resource
    RolesPermissionsMapper rolesPermissionsMapper;
    @Test
    public void testRO(){
        List<RolesPermissions> rolesPermissions = rolesPermissionsMapper.findByRoleId(1L);
        for (RolesPermissions rolesPermissions1:rolesPermissions) {
            System.out.println(rolesPermissions1.getPermissionId());
        }
    }

    @Test
    public void testROle(){
        System.out.println(rolesMapper.findByName("管理员"));
    }
}