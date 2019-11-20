package com.springmall.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by fwj on 2019-11-20.
 */
public class CustomRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * 重写doAuthenticate方法，实现reaml的指定分发
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Collection<Realm> originrRealms = this.getRealms(); // 获取所有的的域
        CustomToken token = (CustomToken) authenticationToken; // 获取自定义的token
        String type = token.getType(); // 获取自定义的token中的type属性
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originrRealm : originrRealms) { // 获取指定类型的域，即域的分发
            if (originrRealm.getName().toLowerCase().contains(type)) {
                realms.add(originrRealm);
            }
        }
        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) : this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
