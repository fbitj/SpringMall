package com.springmall.config;

import com.springmall.shiro.AdminRealm;
import com.springmall.shiro.CustomRealmAuthenticator;
import com.springmall.shiro.CustomSessionManager;
import com.springmall.shiro.WxRealm;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    @Bean
    public DefaultWebSecurityManager securityManager(CustomSessionManager sessionManager,
                                                     AdminRealm adminRealm,
                                                     WxRealm wxRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        securityManager.setRealms(realms);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/auth/login");//
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin/auth/login","anon");
        filterMap.put("/wx/user/login","anon");
        filterMap.put("/admin/auth/info","perms[user:info]");
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public CustomSessionManager sessionManager(){
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setGlobalSessionTimeout(600000);
//        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

   /* @Bean
    public CustomRealmAuthenticator customRealmAuthenticator(AdminRealm adminRealm,WxRealm wxRealm){
        CustomRealmAuthenticator customRealmAuthenticator = new CustomRealmAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customRealmAuthenticator.setRealms(realms);
        return customRealmAuthenticator;
    }*/

}
