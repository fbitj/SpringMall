package com.springmall.controller;

import com.springmall.bean.Admin;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
import com.springmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * Created by fwj on 2019-11-15.
 */

@RestController
@RequestMapping("admin/auth")
@SessionAttributes(names = {"username"})
public class AuthController {
    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    public BaseReqVo<String> login(@RequestBody Admin admin, Model model) {
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        if (adminService.login(admin) == 1) {
            baseReqVo.setErrno(0);
            baseReqVo.setData("6d182056-3821-4a75-ac59-1724a0707524");
            baseReqVo.setErrmsg("成功");
            model.addAttribute("username", admin.getUsername()); // 把用户名添加进Session中
        } else {
            baseReqVo.setErrno(605);
            baseReqVo.setErrmsg("用户账号或密码不正确");
        }
        return  baseReqVo;
    }

    @RequestMapping("info")
    public BaseReqVo getInfo(String token) {
        // 通过请求参数中sessionid获取到session中的管理员系信息
        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("songge");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);
        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


}

