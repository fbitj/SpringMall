package com.springmall.controller.admin;

import com.springmall.bean.Admin;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
import com.springmall.service.AdminService;
import com.springmall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by fwj on 2019-11-15.
 */

@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @Autowired
    AdminService adminService;

//    @RequestMapping("login")
//    public BaseReqVo<String> login(@RequestBody Admin admin, HttpSession session) {
//        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
//        if (adminService.login(admin) == 1) {
//            baseReqVo.setErrno(0);
//            baseReqVo.setData("6d182056-3821-4a75-ac59-1724a0707524");
//            baseReqVo.setErrmsg("成功");
//            session.setAttribute("username", admin.getUsername()); // 把用户名添加进Session中
//        } else {
//            baseReqVo.setErrno(605);
//            baseReqVo.setErrmsg("用户账号或密码不正确");
//        }
//        return  baseReqVo;
//    }

    //鉴权登录
    @RequestMapping("login")
    public BaseReqVo<String> login(@RequestBody Admin admin, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        CustomToken token = new CustomToken(admin.getUsername(), admin.getPassword(), "admin");
        try {
            subject.login(token); // Shiro登录认证，认证失败会抛出IncorrectCredentialsException异常
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            return BaseReqVo.error(605, "用户账号或密码不正确");
        }
        // 获得SessionId返回给浏览器，之后浏览器的每次请求，在请求头中都会携带该SessionId
        Serializable sessionId = subject.getSession().getId();
        return BaseReqVo.ok(sessionId);
    }

    @RequestMapping("info")
    public BaseReqVo getInfo(String token) {
        // 通过请求参数中sessionid获取到session中的管理员系信息
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal(); // 为什么这个就是当前登录用户的信息?

        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData data = new InfoData();
//        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setAvatar(principal.getAvatar());
        data.setName(principal.getUsername());
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*"); //权限暂时写这个
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员"); // 角色暂时写这个
        data.setRoles(roles);
        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    // 登出操作
    @RequestMapping("logout")
    public BaseReqVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseReqVo.ok();
    }


}

