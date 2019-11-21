package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import com.springmall.bean.UserData;
import com.springmall.service.UserService;
import com.springmall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fwj on 2019-11-19.
 */

@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    UserService userService;

    /**
     * 微信端登录，进行shiro认证
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseReqVo login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject(); // 获取subject（主体）
        CustomToken token = new CustomToken(user.getUsername(), user.getPassword(), "user"); // 创建token
        try {
            subject.login(token); // 登录认证
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            return BaseReqVo.error(700, "账号密码不对"); //认证失败
        }
        UserData userData = new UserData(); // 返回的user数据的封装
        User principal = (User) subject.getPrincipal();
        UserData.UserInfoBean userInfo = new UserData.UserInfoBean();
        userInfo.setNickName(user.getUsername());
        userInfo.setAvatarUrl(principal.getAvatar());
        userData.setUserInfo(userInfo);
        userData.setToken(subject.getSession().getId().toString()); // 获取sessionid
        String tokenExpire = new Date(subject.getSession().getTimeout()).toString(); // 获取session的过期时间
        userData.setTokenExpire(tokenExpire);
        return BaseReqVo.ok(userData);
    }

    /**
     * 登出操作
     * @return
     */
    @RequestMapping("logout")
    public BaseReqVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseReqVo.ok();
    }

    /**
     * 密码重置
     * @return
     */
    @RequestMapping("reset")
    public BaseReqVo reset(@RequestBody Map<String,String> dataMap) {
        dataMap.forEach((x,y) -> {
            System.out.println(x + ":" + y);
        });
        return BaseReqVo.ok();
    }

    /**
     * 短信验证码
     * @param mobile
     * @return
     */
    @RequestMapping("regCaptcha")
    public BaseReqVo regCaptcha(@RequestBody String mobile) {
        System.out.println("mobile = " + mobile);
        return BaseReqVo.ok();
    }

    /**
     * 绑定手机
     */
    @RequestMapping("bindPhone")
    public BaseReqVo regCaptcha(@RequestBody String encryptedData, @RequestBody String iv) {
        System.out.println("encryptedData = " + encryptedData);
        System.out.println("iv = " + iv);
        return BaseReqVo.ok();
    }

}
