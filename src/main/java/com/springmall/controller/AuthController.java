package com.springmall.controller;

import com.springmall.bean.Admin;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
<<<<<<< HEAD
import com.springmall.bean.LoginVo;
import com.springmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    public BaseReqVo login(@RequestBody LoginVo loginVo, HttpServletRequest request){
        //1、数据库查询用户是否存在
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();

        List<Admin> admins = adminService.login(loginVo);
        if (admins.size() == 1) {
            // 管理员存在，登陆成功！加入到session
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            session.setAttribute("admin",admins.get(0));
            // 返回
            baseReqVo.setData(sessionId);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        } else if (admins.size() == 0){
//            {"errno":605,"errmsg":"用户帐号或密码不正确"}
            baseReqVo.setErrmsg("用户帐号或密码不正确！");
            baseReqVo.setErrno(605);
        } else {
            baseReqVo.setErrmsg("系统繁忙！");
            baseReqVo.setErrno(404);
=======
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
>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2
        }
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
=======
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
>>>>>>> 2cf51b272762e8935427014aa95bf36ab05295d2
        return baseReqVo;
    }
}
