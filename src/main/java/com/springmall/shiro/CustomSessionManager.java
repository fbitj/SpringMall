package com.springmall.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest  = (HttpServletRequest) request;
        String header = httpServletRequest.getHeader("X-Litemall-Admin-Token");
        if (header != null && !"".equals(header)){
            return header;
        }
        return super.getSessionId(request, response);
    }
}
