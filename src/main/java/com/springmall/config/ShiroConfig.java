package com.springmall.config;

import com.springmall.shiro.AdminRealm;
import com.springmall.shiro.CustomRealmAuthenticator;
import com.springmall.shiro.CustomSessionManager;
import com.springmall.shiro.UserRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by fwj on 2019-11-19.
 */
@Configuration
public class ShiroConfig {

    // 注册安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm, UserRealm userRealm, CustomSessionManager sessionManager, CustomRealmAuthenticator realmAuthenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(userRealm);
        securityManager.setRealms(realms); // 设置多个域
        securityManager.setSessionManager(sessionManager); // 在安全管理器中注册会话管理器
        securityManager.setAuthenticator(realmAuthenticator);  // 设置自定义的relmAuthenticator
        return securityManager;
    }

     // 注册ShiroFilter
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager); // 对shiro过滤器指定安全管理器
        shiroFilterFactoryBean.setLoginUrl("/login/redirect");  // 设置认证失败重定向的url
        // 一定要使用LinkedHashMap，否则，chain的顺序会有问题
        LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
        // 第一个参数是请求url，第二个参数是过滤器
        filterChainMap.put("/admin/auth/login", "anon");
//        filterChainMap.put("/admin/auth/info", "anon");
        filterChainMap.put("/wx/auth/login", "anon");
//        filterChainMap.put("/user/query", "perms[user:query]");  // 采用声明式的鉴权更好
        filterChainMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap); // 设置过滤链Map
        return shiroFilterFactoryBean;
    }

    // 声明式鉴权
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    // 注册自定义的SessionManager
    @Bean
    public CustomSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setDeleteInvalidSessions(true); // 删除失效的Session
        sessionManager.setGlobalSessionTimeout(1000 * 60 * 20); // 全局Session超时时间
        return sessionManager;
    }

    // 注册自定义的RealmAuhenticator
    @Bean
    public CustomRealmAuthenticator customRealmAuthenticator(AdminRealm adminRealm, UserRealm userRealm) {
        CustomRealmAuthenticator customRealmAuthenticator = new CustomRealmAuthenticator();
        List<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(userRealm);
        customRealmAuthenticator.setRealms(realms); // 设置自定义的realm
        return customRealmAuthenticator;
    }
}
