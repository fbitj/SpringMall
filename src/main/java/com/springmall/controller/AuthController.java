package com.springmall.controller;
//import T;
import java.util.ArrayList;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.DashBoard;
import com.springmall.bean.InfoData;
import com.springmall.server.DashBoardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 16:50
 */
@RestController
@RequestMapping("admin")
public class AuthController {

    @RequestMapping("auth/login")
    public BaseReqVo login(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setData("5efcc2c5-3917-4439-8f95-6ec07748ae7f");
        baseReqVo.setErrmsg("成功登陆");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("auth/info")
    public BaseReqVo info(String token){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();

        InfoData infoData = new InfoData();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        infoData.setRoles(roles);
        infoData.setName("admin123");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        infoData.setPerms(perms);
        infoData.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

        baseReqVo.setData(infoData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    @Autowired
    DashBoardImpl dashBoardi;

    @RequestMapping("dashboard")
    public BaseReqVo dashboard(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        DashBoard dashBoard = new DashBoard();
        dashBoard.setGoodsTotal(dashBoardi.getGoodsTotal());
        dashBoard.setUserTotal(dashBoardi.getUserTotal());
        dashBoard.setProductTotal(dashBoardi.getProductTotal());
        dashBoard.setOrderTotal(dashBoardi.getOrderTotal());
        baseReqVo.setData(dashBoard);

        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


}
