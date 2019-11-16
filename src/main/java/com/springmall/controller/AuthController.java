package com.springmall.controller;

import com.springmall.bean.Admin;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.InfoData;
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
        }
        return baseReqVo;
    }

    @RequestMapping("info")
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
