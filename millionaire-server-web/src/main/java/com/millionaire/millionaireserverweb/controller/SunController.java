package com.millionaire.millionaireserverweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireadminservice.module.*;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionaireadminservice.service.PermissionsService;
import com.millionaire.millionaireadminservice.service.RolesPermissionsService;
import com.millionaire.millionaireadminservice.service.RolesService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class SunController {

    @Resource
    private RolesService rolesService;
    @Resource
    private BackstageUsersService backstageUsersService;
    @Resource
    private PermissionsService permissionsService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    private Logger logger = LoggerFactory.getLogger(SunController.class);

    @GetMapping("backstageLoginPage")
    public String backstageLogin() {
        return "登陆页";
    }

    /**
     * 用户登陆
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("backstageLogin")

    public ResultBean subLogin(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        if(name==null){
            return new ResultBean(-1,"账号没传");
        }
        if(password==null){
            return new ResultBean(-1,"密码没传");
        }
        if(name.length()==0){
            return new ResultBean(-1,"请输入账号");
        }
        if(password.length()==0){
            return new ResultBean(-1,"请输入密码");
        }
        BackstageUsers backstageUsers = backstageUsersService.findByName(name);
        if(backstageUsers==null){
            return new ResultBean(-1,"用户名不存在");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
        } catch (Exception e) {
            return new ResultBean(-1,"密码错误");
        }

        Roles roles = rolesService.selectByPrimaryKey(backstageUsers.getRoleId());
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
        return new ResultBean(1,"登陆成功",modulars);
    }

    /**
     * 条件查询所有后台用户
     *
     * @return
     */
    @GetMapping("/a/list/backstageUsers")
    public ResultBean getUsers(Integer pageNum, String name, String roleName) {
        if (pageNum == null) {
            return new ResultBean(-1, "页数不能为空");
        }
        if (name == null) {
            return new ResultBean(-1, "用户名不能为空");
        }
        if (roleName == null) {
            return new ResultBean(-1, "角色名不能为空");
        }

        if (roleName.length() != 0) {
            logger.info("角色不为空");
            Roles roles = rolesService.findByName(roleName);
            if (roles == null) {
                return new ResultBean(-1, "没有这个角色");
            }
            BackstageUsers backstageUsers = new BackstageUsers();
            backstageUsers.setName(name);
            backstageUsers.setRoleId(roles.getId());
            PageHelper.startPage(pageNum, 10);
            List<BackstageUserDTO> list = backstageUsersService.findByNameOrRole(backstageUsers);
            PageInfo pageInfo = new PageInfo(list);
            return new ResultBean(1, "请求成功", pageInfo);
        }
        String regex = "\\S*";
        if (!name.matches(regex)) {
            return new ResultBean(-1, "非法字符");
        }
        logger.info("用户不为空");
        BackstageUsers backstageUsers = new BackstageUsers();
        backstageUsers.setName(name);
        PageHelper.startPage(pageNum, 10);
        List<BackstageUserDTO> list = backstageUsersService.findByNameOrRole(backstageUsers);
        PageInfo pageInfo = new PageInfo(list);
        return new ResultBean(1, "请求成功", pageInfo);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping("/a/backstageUser/{id}")
    public ResultBean deleteById(@PathVariable Long id) {
        backstageUsersService.deleteByPrimaryKey(id);
        return new ResultBean(1, "删除用户成功");
    }

    /**
     * 增加用户
     *
     * @param jsonObject
     */
    @PostMapping("/a/backstageUsers")
    public ResultBean insertUser(@RequestBody JSONObject jsonObject ) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
        String phone = jsonObject.getString("phone");
        String roleName = jsonObject.getString("roleName");
        if (name == null) {
            return new ResultBean(-1, "用户名没有传");
        }
        if (password == null) {
            return new ResultBean(-1, "密码没有传");
        }
        if (rePassword == null) {
            return new ResultBean(-1, "重复密码没有传");
        }
        if (phone == null) {
            return new ResultBean(-1, "电话没有传");
        }
        if (roleName == null) {
            return new ResultBean(-1, "角色名没有传");
        }
        if (name.length() == 0) {
            return new ResultBean(-1, "用户名不能为空");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "密码不能为空");
        }
        if (rePassword.length() == 0) {
            return new ResultBean(-1, "重复密码不能为空");
        }
        if (phone.length() == 0) {
            return new ResultBean(-1, "电话不能为空");
        }
        if (roleName.length() == 0) {
            return new ResultBean(-1, "角色不能为空");
        }
        if (!password.equals(rePassword)) {
            return new ResultBean(-1, "两次密码不相同");
        }
        String regex = "\\S*";

        Roles roles = rolesService.findByName(roleName);
        BackstageUsers backstageUsers = new BackstageUsers();
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        String salt = String.valueOf(new Random().nextInt(899999) + 100000);
        String md5HashPassword = new Md5Hash(password, salt, 2).toString();
        backstageUsers.setName(name);
        backstageUsers.setPassword(md5HashPassword);
        backstageUsers.setSalt(salt);
        backstageUsers.setPhone(phone);
        backstageUsers.setRoleId(roles.getId());
        backstageUsers.setGmtUpdate(System.currentTimeMillis());
        backstageUsers.setGmtCreate(System.currentTimeMillis());
        backstageUsers.setFounder(userName);
        backstageUsers.setModifier(userName);
        backstageUsersService.insert(backstageUsers);
        return new ResultBean(1, "添加成功");
    }

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/a/backstageUser/{id}")
    public ResultBean getBackstageUserById(@PathVariable Long id) {
        BackstageUserDTO backstageUser = backstageUsersService.findById(id);
        return new ResultBean(1, "查询成功", backstageUser);
    }

    /**
     * 修改用户
     *
     * @param jsonObject
     * @param id
     * @return
     */
    @RequestMapping(value = "/a/backstageUser/{id}", method = RequestMethod.PUT)
    public ResultBean updateById(@RequestBody JSONObject jsonObject,
                                 @PathVariable("id") Long id) {
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
        String phone = jsonObject.getString("phone");
        String roleName = jsonObject.getString("roleName");
        if (password == null) {
            return new ResultBean(-1, "密码没有传");
        }
        if (rePassword == null) {
            return new ResultBean(-1, "重复密码没有传");
        }
        if (phone == null) {
            return new ResultBean(-1, "电话没有传");
        }
        if (roleName == null) {
            return new ResultBean(-1, "角色名没有传");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "密码不能为空");
        }
        if (rePassword.length() == 0) {
            return new ResultBean(-1, "重复密码不呢为空");
        }
        if (phone.length() == 0) {
            return new ResultBean(-1, "电话不能位空");
        }
        if (roleName.length() == 0) {
            return new ResultBean(-1, "角色不能为空");
        }
        if (!password.equals(rePassword)) {
            return new ResultBean(-1, "两次密码不相同");
        }
        Roles roles = rolesService.findByName(roleName);
        if (roles == null) {
            return new ResultBean(-1, "角色为空");
        }
        BackstageUsers backstageUsers = new BackstageUsers();
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        String salt = String.valueOf(new Random().nextInt(899999) + 100000);
        String md5HashPassword = new Md5Hash(password, salt, 2).toString();
        backstageUsers.setPassword(md5HashPassword);
        backstageUsers.setSalt(salt);
        backstageUsers.setPhone(phone);
        backstageUsers.setRoleId(roles.getId());
        backstageUsers.setGmtUpdate(System.currentTimeMillis());
        backstageUsers.setModifier(userName);
        backstageUsers.setId(id);
        backstageUsersService.updateByPrimaryKey(backstageUsers);
        return new ResultBean(1, "修改成功");
    }

    /**
     * 修改密码
     *
     * @param jsonObject
     * @return
     */
    @PutMapping("/a/backstageUser/password")
    public ResultBean updatePassword(@RequestBody JSONObject jsonObject) {
        String oldPassword = jsonObject.getString("oldPassword");
        String password = jsonObject.getString("password");
        String rePassword = jsonObject.getString("rePassword");
        if (oldPassword == null) {
            return new ResultBean(-1, "旧密码没有传");
        }
        if (password == null) {
            return new ResultBean(-1, "密码没有传");
        }
        if (rePassword == null) {
            return new ResultBean(-1, "重复密码没有传");
        }
        if (oldPassword.length() == 0) {
            return new ResultBean(-1, "旧密码不能为空");
        }
        if (password.length() == 0) {
            return new ResultBean(-1, "密码不能为空");
        }
        if (rePassword.length() == 0) {
            return new ResultBean(-1, "重复密码不能为空");
        }
        if (!password.equals(rePassword)) {
            return new ResultBean(-1, "两次密码输入不一致");
        }
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        BackstageUsers backstageUsers = backstageUsersService.findByName(userName);
        Long id = backstageUsers.getId();
        String salt = backstageUsers.getSalt();
        String old = backstageUsers.getPassword();
        String md5HashOldPassword = new Md5Hash(oldPassword, salt, 2).toString();
        if (!md5HashOldPassword.equals(old)) {
            return new ResultBean(-1, "旧密码错误");
        }
        String newSalt = String.valueOf(new Random().nextInt(899999) + 100000);
        String md5HashPassword = new Md5Hash(password, newSalt, 2).toString();
        BackstageUsers users = new BackstageUsers();
        users.setPassword(md5HashPassword);
        users.setSalt(newSalt);
        users.setId(id);
        users.setGmtUpdate(System.currentTimeMillis());
        users.setModifier(userName);
        backstageUsersService.updatePassword(users);
        return new ResultBean(1, "修改成功");
    }

    /**
     * 获取角色分页
     *
     * @return
     */
    @GetMapping("/a/list/roles")
    public ResultBean getRoles(Integer pageNum) {
        if (pageNum == null) {
            return new ResultBean(-1, "页数不能为空");
        }
        PageHelper.startPage(pageNum, 10);
        List<Roles> list = rolesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return new ResultBean(1, "查询成功", pageInfo);
    }

    /**
     * 获得所有
     *
     * @return
     */
    @GetMapping("/a/roles")
    public ResultBean getRolesAll() {
        return new ResultBean(1, "查询成功", rolesService.findAll());
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/a/role/{id}")
    public ResultBean deleteRole(@PathVariable Long id) {
        rolesService.deleteByPrimaryKey(id);
        return new ResultBean(1, "删除成功");
    }

    /**
     * 添加角色，还能赋予权限。
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/a/roles")
    public ResultBean insertRole(@RequestBody JSONObject jsonObject) {
        String roleName = jsonObject.getString("roleName");
        String permissions = jsonObject.getString("permissions");
        if (roleName == null) {
            return new ResultBean(-1, "角色名称没有传");
        }
        if (permissions == null) {
            return new ResultBean(-1, "权限id没有传");
        }
        if (roleName.length() == 0) {
            return new ResultBean(-1, "角色不能为空");
        }
        Roles role = rolesService.findByName(roleName);
        if (role != null) {
            return new ResultBean(-1, "角色名已经存在");
        }
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        Roles roles = new Roles();
        roles.setRoleName(roleName);
        roles.setGmtCreate(System.currentTimeMillis());
        roles.setGmtUpdate(System.currentTimeMillis());
        roles.setFounder(userName);
        roles.setModifier(userName);
        Long roleId = rolesService.insert(roles);
        if (permissions.length() == 0) {
            return new ResultBean(1, "添加成功");
        }
        List<Long> list = JSON.parseArray(permissions, Long.class);
        logger.info("角色id:" + roleId);
        if (roleId == null) {
            return new ResultBean(-1, "角色id不能为空");
        }
        for (Long permission : list) {
            RolesPermissions rolesPermissions = new RolesPermissions();
            rolesPermissions.setRoleId(roleId);
            rolesPermissions.setPermissionId(permission);
            rolesPermissions.setGmtCreate(System.currentTimeMillis());
            rolesPermissions.setGmtUpdate(System.currentTimeMillis());
            rolesPermissionsService.insert(rolesPermissions);
        }
        return new ResultBean(1, "添加成功");
    }

    /**
     * 更新角色的权限
     *
     * @param id
     * @param jsonObject
     * @return
     */
    @PutMapping("/a/roles/{id}")
    public ResultBean updateRole(@PathVariable Long id,
                                 @RequestBody JSONObject jsonObject) {
        String roleName = jsonObject.getString("roleName");
        String permissions = jsonObject.getString("permissions");
        if (roleName == null) {
            return new ResultBean(-1, "角色名称没有传");
        }
        if (permissions == null) {
            return new ResultBean(-1, "权限id的集合没传");
        }
        if (roleName.length() == 0) {
            return new ResultBean(-1, "角色名不能为空");
        }
        Roles role = rolesService.findByName(roleName);
        if (role != null && role.getId() != id) {
            return new ResultBean(-1, "用户名已经存在");
        }
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        Roles roles = new Roles();
        roles.setRoleName(roleName);
        roles.setGmtUpdate(System.currentTimeMillis());
        roles.setModifier(userName);
        roles.setId(id);
        rolesService.updateByPrimaryKey(roles);
        List<RolesPermissions> rolesPermissionsList = rolesPermissionsService.findByRoleId(id);
        for (RolesPermissions rolesPermission : rolesPermissionsList) {
            Long rolePermissionId = rolesPermission.getId();
            rolesPermissionsService.deleteByPrimaryKey(rolePermissionId);
        }
        if (permissions.length() == 0) {
            return new ResultBean(1, "更新成功");
        }
        List<Long> list = JSON.parseArray(permissions, Long.class);
        for (Long permission : list) {
            RolesPermissions rolesPermissions = new RolesPermissions();
            rolesPermissions.setRoleId(id);
            rolesPermissions.setPermissionId(permission);
            rolesPermissions.setGmtCreate(System.currentTimeMillis());
            rolesPermissions.setGmtUpdate(System.currentTimeMillis());
            rolesPermissionsService.insert(rolesPermissions);
        }
        return new ResultBean(1, "更新成功");
    }

    /**
     * 获得角色的权限id通过角色id
     *
     * @param id
     * @return
     */
    @GetMapping("/a/rolePermission/{id}")
    public ResultBean selectRolePermission(@PathVariable Long id) {
        Map map = new HashMap();
        String roleName = rolesService.selectByPrimaryKey(id).getRoleName();
        map.put("roleName", roleName);
        List<Long> permissionsId = new ArrayList<>();
        List<RolesPermissions> rolesPermissions = rolesPermissionsService.findByRoleId(id);
        for (RolesPermissions rolesPermission : rolesPermissions) {
            Long permissionId = rolesPermission.getPermissionId();
            permissionsId.add(permissionId);
        }
        map.put("permissionsId", permissionsId);
        return new ResultBean(1,"请求成功",map);
    }

    /**
     * 返回所有权限
     *
     * @return
     */
    @GetMapping("/a/permission")
    public ResultBean getPermission() {
        List<Permissions> permissions = permissionsService.findAll();
        Modular modular1 = new Modular();
        Modular modular2 = new Modular();
        Modular modular3 = new Modular();
        Modular modular4 = new Modular();
        List<Permissions> list1 = new ArrayList<>();
        List<Permissions> list2 = new ArrayList<>();
        List<Permissions> list3 = new ArrayList<>();
        List<Permissions> list4 = new ArrayList<>();
        for (Permissions permission : permissions) {
            if (permission.getId() > 0 && permission.getId() < 10) {
                list1.add(permission);
            }
            if (permission.getId() > 10 && permission.getId() < 20) {
                list2.add(permission);
            }
            if (permission.getId() > 20 && permission.getId() < 30) {
                list3.add(permission);
            }
            if (permission.getId() > 30 && permission.getId() < 40) {
                list4.add(permission);
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
        return new ResultBean(1, "请求成功", modulars);
    }
}

