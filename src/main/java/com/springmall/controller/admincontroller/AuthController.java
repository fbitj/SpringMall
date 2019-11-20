package com.springmall.controller.admincontroller;

import com.springmall.bean.Admin;
import com.springmall.bean.BaseRespVo;
import com.springmall.bean.InfoData;
import com.springmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public BaseRespVo<String> login(@RequestBody Admin admin) {
        BaseRespVo<String> BaseRespVo = new BaseRespVo<>();
//        if (adminService.login(admin) == 1) {
            BaseRespVo.setErrno(0);
            BaseRespVo.setData("6d182056-3821-4a75-ac59-1724a0707524");
            BaseRespVo.setErrmsg("成功");
        /*} else {
            BaseRespVo.setErrno(605);
            BaseRespVo.setErrmsg("用户账号或密码不正确");
        }*/
        return  BaseRespVo;
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


}

