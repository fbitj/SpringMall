package com.springmall.shiro;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by fwj on 2019-11-19.
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    // 重写获取SessionId的方法，若可以从前端的reqeustHeader中获取SessionId，则直接返回获取的SessionId
    // 否则生成新的SessionId返回。
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String sessionId = httpServletRequest.getHeader("X-Litemall-Admin-Token"); //获取Admin的token
        if (sessionId == null || "".equals(sessionId)) { // 获取小程序端user的token
            sessionId = httpServletRequest.getHeader("X-cskaoyanmall-Admin-Token");
        }
        if (sessionId != null && !"".equals(sessionId)) { // 若请求头中包含SessionId，则直接返回
            return sessionId;
        }
        return super.getSessionId(request, response);  // 请求头中不包含SessionId，则创建新的SessionId返回
    }
}
