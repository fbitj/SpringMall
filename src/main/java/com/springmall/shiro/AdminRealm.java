package com.springmall.shiro;

import com.springmall.bean.Admin;
import com.springmall.bean.Admin2;
import com.springmall.mapper.AdminMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by fwj on 2019-11-19.
 */

@Component
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 根据用户名查询用户的密码，并返回认证信息
     * @param authenticationToken
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; // 获取登录时传入的token
        String username = token.getUsername(); // 获取用户名
        Admin admin = adminMapper.selectAdminByUsername(username); // 根据用户名查询出认证信息
        if (admin == null) { //查询结果为空直接返回null
            return null;
        }
        String passwordByDb = admin.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, passwordByDb, this.getName());
        return authenticationInfo;
    }

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin primaryPrincipal = (Admin) principalCollection.getPrimaryPrincipal();
        String roleIds = primaryPrincipal.getRoleIds();
        // 将roleIds字符串转换为Integer类型的List
        String[] splitRoles = roleIds.replace("[", "").replace("]", "").replaceAll(" ", "").split(",");
        ArrayList<Integer> rolesIdList = new ArrayList<>();
        for (String splitRole : splitRoles) {
            rolesIdList.add(Integer.valueOf(splitRole));
        }
        // 从数据库查询权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        HashSet<String> permsSet = new HashSet<>();
        if (rolesIdList.contains(1)) { // 若为超级管理员，直接查询所有接口
            List<String> permsList = adminMapper.selectAllPermission();
            authorizationInfo.addStringPermissions(permsList);
            return authorizationInfo;
        }
        for (Integer roleId : rolesIdList) {
            List<String> permsList = adminMapper.selectPermissionByRoldId(roleId);
            for (String perm : permsList) {
                permsSet.add(perm);
            }
        }
        authorizationInfo.addStringPermissions(permsSet);
        return authorizationInfo;
    }


}
