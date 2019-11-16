package com.springmall.controller;

import com.springmall.bean.Admin;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
import com.springmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by fwj on 2019-11-15.
 */

@Controller
@RequestMapping("admin/auth")
public class AuthController {
    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public BaseReqVo<String> login(@RequestBody Admin admin) {
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        if (adminService.login(admin) == 1) {
            baseReqVo.setErrno(0);
            baseReqVo.setData("6d182056-3821-4a75-ac59-1724a0707524");
            baseReqVo.setErrmsg("成功");
        }else {
            baseReqVo.setErrno(605);
            baseReqVo.setErrmsg("用户账号或密码不正确");
        }
        return baseReqVo;
    }

    @RequestMapping("info")
    @ResponseBody
    public BaseReqVo info(String token) {
        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData infoData = new InfoData();
        ArrayList<String> roleList = new ArrayList<>();
        roleList.add("超级管理员");
        infoData.setRoles(roleList);
        infoData.setName("admin");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        infoData.setPerms(perms);
        infoData.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        baseReqVo.setErrno(0);
        baseReqVo.setData(infoData);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
