package com.springmall.utils;

import com.springmall.bean.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

@Controller
public class GetUserUtil {
    public String getUsername() {
        //获取session里的username
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        String username = principal.getUsername();
        return username;
    }
    public int getUserId() {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if(principal != null) {
            Integer userId = principal.getId();
            return userId;
        }
            return -1;
        }
    }

