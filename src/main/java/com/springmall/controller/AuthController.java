package com.springmall.controller;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
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
        return baseReqVo;
    }

    @RequestMapping("info")
    public BaseReqVo info(String token){

        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData infoData = new InfoData();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("商场管理员");
        infoData.setRoles(roles);
        infoData.setName("admin123");
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
