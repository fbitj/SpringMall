package com.springmall.controller.admincontroller;

import com.springmall.annotation.ActionType;
import com.springmall.annotation.ControllerLog;
import com.springmall.bean.Admin;
import com.springmall.bean.BaseRespVo;
import com.springmall.bean.InfoData;
import com.springmall.service.AdminService;
import com.springmall.shiro.CustomToken;
import com.springmall.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @RequestMapping("login")
    @ControllerLog(actionType = ActionType.LOGIN,description = "用户登陆")
    public BaseRespVo<String> login(@Valid @RequestBody Admin admin) {
        String username = admin.getUsername();
        String md5EncryptPassword = Md5Utils.getDefaultMd5Encrypt(admin.getPassword());
        CustomToken token = new CustomToken(username, md5EncryptPassword, "admin");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.failed("用户名或密码不正确，请重试！",605);
        }
        Serializable id = subject.getSession().getId();
        return BaseRespVo.ok(id);
    }

    @RequestMapping("info")
    public BaseRespVo getInfo(String token) {
        // 通过请求参数中sessionid获取到session中的管理员系信息
        BaseRespVo BaseRespVo = new BaseRespVo();
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("songge");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);
        BaseRespVo.setData(data);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    @RequestMapping("logout")
    public BaseRespVo logout(String token) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.ok();
    }

}

