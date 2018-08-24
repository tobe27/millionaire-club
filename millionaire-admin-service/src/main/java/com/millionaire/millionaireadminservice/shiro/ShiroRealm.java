package com.millionaire.millionaireadminservice.shiro;

import com.millionaire.millionaireadminservice.module.BackstageUsers;
import com.millionaire.millionaireadminservice.module.Roles;
import com.millionaire.millionaireadminservice.module.RolesPermissions;
import com.millionaire.millionaireadminservice.service.BackstageUsersService;
import com.millionaire.millionaireadminservice.service.PermissionsService;
import com.millionaire.millionaireadminservice.service.RolesPermissionsService;
import com.millionaire.millionaireadminservice.service.RolesService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private BackstageUsersService backstageUsersService;
    @Resource
    private RolesService rolesService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    @Resource
    private PermissionsService permissionsService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //查询角色名称
        BackstageUsers backstageUsers = backstageUsersService.findByName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色权限
        Long roleId = backstageUsers.getRoleId();
        Roles roles = rolesService.selectByPrimaryKey(roleId);
        simpleAuthorizationInfo.addRole(roles.getRoleName());
        //通过角色id来查询权限id
        List<RolesPermissions> rolesPermissions = rolesPermissionsService.findByRoleId(roles.getId());
        for (RolesPermissions rolesPermission : rolesPermissions) {
            System.out.println(rolesPermission.getPermissionId());

            String permission = permissionsService.selectByPrimaryKey(rolesPermission.getPermissionId()).getPermission();
            System.out.println(permission);
            simpleAuthorizationInfo.addStringPermission(permission);
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String userName = (String) authenticationToken.getPrincipal();
        System.out.println(userName);
//
        BackstageUsers backstageUsers= backstageUsersService.findByName(userName);
        String password = backstageUsers.getPassword();
        if (password == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
            String salt = backstageUsers.getSalt();
            System.out.println(salt);
            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));
            return simpleAuthenticationInfo;
        }
    }
}
