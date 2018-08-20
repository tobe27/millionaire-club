package com.millionaire.millionaireadminservice.controller;

import com.millionaire.millionaireadminservice.module.*;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionaireadminservice.service.PermissionsService;
import com.millionaire.millionaireadminservice.service.RolesPermissionsService;
import com.millionaire.millionaireadminservice.service.RolesService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @GetMapping("backstageLogin")
    public String backstageLogin(){
        return "登陆页";
    }

    /**
     * 用户登陆
     * @param name
     * @param password
     * @return
     */
    @PostMapping("subLogin")
    public List<Modular> subLogin(String name, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try{
            subject.login(token);
        }catch (Exception e){
            return null;
        }
        BackstageUsers backstageUsers = backstageUsersService.findByName(name);
        Roles roles = rolesService.findByName(backstageUsers.getRole());
        //通过角色id来查询权限id
        Modular modular1 = new Modular();
        Modular modular2 = new Modular();
        Modular modular3 = new Modular();
        Modular modular4 = new Modular();
        List<Permissions> list1 = new ArrayList<>();
        List<Permissions> list2 = new ArrayList<>();
        List<Permissions> list3 = new ArrayList<>();
        List<Permissions> list4 = new ArrayList<>();
        List<RolesPermissions> rolesPermissions = rolesPermissionsService.findByRoleId(roles.getId());
        for (RolesPermissions rolesPermission : rolesPermissions) {
            if (rolesPermission.getPermissionId() > 0 && rolesPermission.getPermissionId() < 10) {
                list1.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
            if (rolesPermission.getPermissionId() > 10 && rolesPermission.getPermissionId() < 20) {
                list2.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
            if (rolesPermission.getPermissionId() > 20 && rolesPermission.getPermissionId() < 30) {
                list3.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
            if (rolesPermission.getPermissionId() > 30 && rolesPermission.getPermissionId() < 40) {
                list4.add(permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()));
            }
        }
        List<Modular> modulars = new ArrayList<>();
        if (list1.size() != 0) {
            modular1.setId(1L);
            modular1.setName("业务管理");
            modular1.setPermissionsList(list1);
            modulars.add(modular1);
        }
        if (list2.size() != 0) {
            modular2.setId(2L);
            modular2.setName("运营管理");
            modular2.setPermissionsList(list2);
            modulars.add(modular2);
        }
        if (list3.size() != 0) {
            modular3.setId(3L);
            modular3.setName("统计信息");
            modular3.setPermissionsList(list3);
            modulars.add(modular3);
        }
        if (list4.size() != 0) {
            modular4.setId(4L);
            modular4.setName("后台管理");
            modular4.setPermissionsList(list4);
            modulars.add(modular4);
        }
        return modulars;
    }

    /**
     * 查询所有后台用户
     * @return
     */
    @GetMapping("/a/list/users")
    public List<BackstageUsers> getAll(){
        return backstageUsersService.findAll();
    }

    /**
     * 模糊查询
     * @param name
     * @param role
     * @return
     */
    @GetMapping("/a/list/user/name")
    public List<BackstageUsers> getByName(String name,String role){
        BackstageUsers backstageUsers= new BackstageUsers();
        backstageUsers.setRole(role);
        backstageUsers.setName(name);
        return backstageUsersService.findByNameOrRole(backstageUsers);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/a/user/{id}")
    public void deleteById(@PathVariable Long id){
        backstageUsersService.deleteByPrimaryKey(id);
    }

    /**
     * 增加用户
     * @param
     */
    @PostMapping("/a/users")
    public String insertUser(String name,
                           String password,
                           String rePassword,
                           String phone,
                           String role){
        if(!password.equals(rePassword)){
            return "两次密码不相同！";
        }
        BackstageUsers backstageUsers = new BackstageUsers();
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        String salt = String.valueOf(new Random().nextInt(899999)+100000);
        String md5HashPassword = new Md5Hash(password,salt,2).toString();
        backstageUsers.setName(name);
        backstageUsers.setPassword(md5HashPassword);
        backstageUsers.setSalt(salt);
        backstageUsers.setPhone(phone);
        backstageUsers.setRole(role);
        backstageUsers.setGmtUpdate(System.currentTimeMillis());
        backstageUsers.setGmtCreate(System.currentTimeMillis());
        backstageUsers.setFounder(userName);
        backstageUsers.setModifier(userName);
        backstageUsersService.insert(backstageUsers);
        return "添加成功"+backstageUsers.getId();
    }

    @RequestMapping(value = "/a/user/fg", method = RequestMethod.PUT)
    public String updateById(@RequestParam String name,
                             @RequestParam("password") String password,
                             @RequestParam("rePassword") String rePassword,
                             @RequestParam("phone") String phone,
                             @RequestParam("role") String role
//                             @PathVariable("id") Long id
)
    {

        System.out.println(name);
//        if(!password.equals(rePassword)){
//            return "两次密码不相同！";
//        }
//        BackstageUsers backstageUsers = new BackstageUsers();
//        String userName=(String)SecurityUtils.getSubject().getPrincipal();
//        String salt = String.valueOf(new Random().nextInt(899999)+100000);
//        String md5HashPassword = new Md5Hash(password,salt,2).toString();
//        backstageUsers.setName(name);
//        backstageUsers.setPassword(md5HashPassword);
//        backstageUsers.setSalt(salt);
//        backstageUsers.setPhone(phone);
//        backstageUsers.setRole(role);
//        backstageUsers.setGmtUpdate(System.currentTimeMillis());
//        backstageUsers.setModifier(userName);
//        backstageUsers.setId(id);
//        backstageUsersService.updateByPrimaryKey(backstageUsers);
        return "修改成功";
    }
}
