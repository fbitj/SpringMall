package com.springmall.shiro;

import com.springmall.bean.Admin;
import com.springmall.mapper.AdminMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
//        Admin primaryPrincipal = (Admin) principalCollection.getPrimaryPrincipal();
//        String username = primaryPrincipal.getUsername();
//        List<String> perms = adminMapper.selectPermsByUsername(username);
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addStringPermissions(perms);
//        return authorizationInfo;
        return null;
    }


}
