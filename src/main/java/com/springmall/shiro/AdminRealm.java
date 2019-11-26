package com.springmall.shiro;

import com.springmall.bean.Admin;
import com.springmall.bean.AdminExample;
import com.springmall.bean.PermissionExample;
import com.springmall.mapper.AdminMapper;
import com.springmall.mapper.PermissionMapper;
import com.springmall.mapper.RoleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PermissionMapper permissionMapper;
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        Admin admin = adminMapper.queryAdminByName(name);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin,admin.getPassword(),getName());
        return authenticationInfo;
    }
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        // 通过用户名查询用户的权限
        String roleIds = admin.getRoleIds().replace("[","").replace("]","");
        // 通过roleid是查询用户具有的权限
        List<String> permissions = permissionMapper.queryPermissionsInRoleIds(roleIds);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }


}
