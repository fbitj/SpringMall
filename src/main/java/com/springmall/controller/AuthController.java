package com.springmall.controller;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
<<<<<<< HEAD
import com.springmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
=======
import com.springmall.bean.LoginVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @RequestMapping("login")
    public BaseReqVo login(@RequestBody LoginVo loginVo){


        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData("e8f7eccf-0b60-4083-9f07-6344e285cd18");
        baseReqVo.setErrmsg("成功");
>>>>>>> dev
        return baseReqVo;
    }

    @RequestMapping("info")
<<<<<<< HEAD
    public BaseReqVo getInfo(String token){
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
