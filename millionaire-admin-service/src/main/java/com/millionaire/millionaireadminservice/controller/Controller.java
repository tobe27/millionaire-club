package com.millionaire.millionaireadminservice.controller;

import com.millionaire.millionaireadminservice.module.*;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionaireadminservice.service.PermissionsService;
import com.millionaire.millionaireadminservice.service.RolesPermissionsService;
import com.millionaire.millionaireadminservice.service.RolesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @Resource
    private RolesService rolesService;
    @Resource
    private BackstageUsersService backstageUsersService;
    @Resource
    private PermissionsService permissionsService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    @GetMapping("subLogin")
     public List<Modular> subLogin(String name,String password){
        BackstageUsers backstageUsers = backstageUsersService.findByName("孙壮壮");
        Roles roles = rolesService.findByName(backstageUsers.getRole());
        Modular modular1 = new Modular();
        Modular modular2 = new Modular();
        Modular modular3 = new Modular();
        Modular modular4 = new Modular();
        List<Permissions> list1 = new ArrayList<>();
        List<Permissions> list2 = new ArrayList<>();
        List<Permissions> list3 = new ArrayList<>();
        List<Permissions> list4 = new ArrayList<>();
        //通过角色id来查询权限id
        List<RolesPermissions> rolesPermissions = rolesPermissionsService.findByRoleId(roles.getId());
        for (RolesPermissions rolesPermission:rolesPermissions) {
            if(rolesPermission.getPermissionId()>0&&rolesPermission.getPermissionId()<10){
                list1.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
            if(rolesPermission.getPermissionId()>10&&rolesPermission.getPermissionId()<20){
                list2.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
            if(rolesPermission.getPermissionId()>20&&rolesPermission.getPermissionId()<30){
                list3.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
            if(rolesPermission.getPermissionId()>30&&rolesPermission.getPermissionId()<40){
                list4.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
        }
        List<Modular> modulars = new ArrayList<Modular>();
        if(list1.size()!=0){
            modular1.setId(1L);
            modular1.setName("业务管理");
            modular1.setPermissionsList(list1);
            modulars.add(modular1);
        }
       if (list2.size()!=0){
           modular2.setId(2L);
           modular2.setName("运营管理");
           modular2.setPermissionsList(list2);
           modulars.add(modular2);
       }
       if(list3.size()!=0){
           modular3.setId(3L);
           modular3.setName("统计信息");
           modular3.setPermissionsList(list3);
           modulars.add(modular3);
       }
       if(list4.size()!=0){
           modular4.setId(4L);
           modular4.setName("后台管理");
           modular4.setPermissionsList(list4);
           modulars.add(modular4);
       }
        return modulars;
    }
}
