package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import com.springmall.bean.UserData;
import com.springmall.component.AliyunComponent;
import com.springmall.service.CouponService;
import com.springmall.service.UserService;
import com.springmall.shiro.CustomToken;
import com.springmall.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    CouponService couponService;

    HashMap<String, String> codeMap = new HashMap<>();
    /**
     * 微信端账号登录，进行shiro认证
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseReqVo login(@RequestBody User user) {
        // 对密码进行MD5加密
        String encryptPwd = Md5Utils.getDefaultMd5Encrypt(user.getPassword());
        user.setPassword(encryptPwd);
        // Shiro登录
        Subject subject = SecurityUtils.getSubject(); // 获取subject（主体）
        CustomToken token = new CustomToken(user.getUsername(), user.getPassword(), "user"); // 创建token
        try {
            subject.login(token); // 登录认证
        } catch (AuthenticationException e) {
            return BaseReqVo.error(703, "账号密码不对"); //认证失败
        }
        // 封装返回数据
        UserData userData = new UserData(); // 返回的user数据的封装
        User principal = (User) subject.getPrincipal();
        UserData.UserInfoBean userInfo = new UserData.UserInfoBean();
        userInfo.setNickName(user.getUsername());
        userInfo.setAvatarUrl(principal.getAvatar());
//        userInfo.setAvatarUrl("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"); // 设置用户头像
        userInfo.setAvatarUrl("http://wx4.sinaimg.cn/orj360/861756fcly1g7od5view7j20b40b4ab0.jpg"); // 设置用户头像
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
    public BaseReqVo logout(HttpServletRequest request) {
        // 记录登出时间和登陆的ip地址
        String ip = request.getRemoteAddr();
        userService.recordUserLoginInfo(ip);
        // Shiro登出
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseReqVo.ok();
    }


    @Autowired
    AliyunComponent aliyunComponent;
    /**
     * 短信验证码
     * @param map:key=mobile封装了电话号码
     * @return
     */
    @RequestMapping("regCaptcha")
    public BaseReqVo regCaptcha(@RequestBody Map<String,String> map) {
        String mobile = map.get("mobile");
        String code = aliyunComponent.sendSms(mobile);
        codeMap.put(mobile,code);
        return BaseReqVo.ok();
    }

    /**
     * 微信账号注册
     * 请求数据
     * {
     *     "username": "songge",
     *     "password": "111111",
     *     "mobile": "13455447452",
     *     "code": "1122",
     *     "wxCode": "043wNK942fjt8P0lrb7429EQ942wNK9U"
     * }
     * 返回数据和登录时一样
     */
    @RequestMapping("register")
    public BaseReqVo register(@RequestBody HashMap<String, String> userInfoMap) {
        // 判断验证码是否相同
//        String userCode = userInfoMap.get("code");
//        if (!isCodeEquals(userCode)){
//            return BaseReqVo.error(500,"验证码不正确");
//        }
        // 判断是否已经存在该用户,0表示不存在，否则存在
        int isUserExist = userService.isUserExist(userInfoMap.get("username"));
        if (isUserExist != 0) {
            return BaseReqVo.error(700, "该用户名已被注册"); //认证失败
        }
        // 判断是否已经存在该手机号,0表示不存在，否则存在
        int isMobileExist = userService.isMobileExist(userInfoMap.get("mobile"));
        if (isMobileExist != 0) {
            return BaseReqVo.error(700, "该手机号已被注册"); //认证失败
        }
        // 对密码进行MD5加密
        String encryptPwd = Md5Utils.getDefaultMd5Encrypt(userInfoMap.get("password"));
        userInfoMap.put("password", encryptPwd);
        // 用户注册，将用户信息插入数据库
        int res = userService.register(userInfoMap);
        if (res == 0)
            return BaseReqVo.error(703, "注册失败，请稍后再试");
        //分发注册赠券
        couponService.sendRegistCoupon(userInfoMap.get("username"));
        //Shiro登录
        Subject subject = SecurityUtils.getSubject(); // 获取subject（主体）
        CustomToken token = new CustomToken(userInfoMap.get("username"), userInfoMap.get("password"), "user"); // 创建token
        try {
            subject.login(token); // 登录认证
        } catch (AuthenticationException e) {
            return BaseReqVo.error(700, "注册认证失败，请稍后再试"); //认证失败
        }
        // 封装返回数据
        UserData userData = new UserData(); // 返回的user数据的封装
        User principal = (User) subject.getPrincipal();
        UserData.UserInfoBean userInfo = new UserData.UserInfoBean();
        userInfo.setNickName(principal.getUsername());
        userInfo.setAvatarUrl(principal.getAvatar());
        userInfo.setAvatarUrl("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"); // 设置用户头像
        userData.setUserInfo(userInfo);
        userData.setToken(subject.getSession().getId().toString()); // 获取sessionid
        String tokenExpire = new Date(subject.getSession().getTimeout()).toString(); // 获取session的过期时间
        userData.setTokenExpire(tokenExpire);
        return BaseReqVo.ok(userData);
    }

    private boolean isCodeEquals(String userCode,String mobile) {
        String code = codeMap.get(mobile);
        if (code != null && code.equals(userCode)){
            return true;
        }
        return false;
    }

    /**
     * 密码重置
     * 接收参数格式：
     *  {
     *     "mobile": "13544458745",
     *     "code": "1234",
     *     "password": "1234"
     * }
     * 失败返回格式：
     * {"errno":703,"errmsg":"验证码错误"}
     */
    @RequestMapping("reset")
    public BaseReqVo reset(@RequestBody Map<String,String> dataMap) {
        // 判断验证码是否相等
        String userCode = dataMap.get("code");
        String mobile = dataMap.get("mobile");
        if (!isCodeEquals(userCode,mobile)){
            return BaseReqVo.error(500,"验证码不正确");
        }
        // 判断数据库是否存在该手机号
        int isMobileExist = userService.isMobileExist(dataMap.get("mobile"));
        if (isMobileExist == 0) {
            return BaseReqVo.error(703, "该手机号未被注册"); //认证失败
        }
        // 根据手机号,重置数据库密码
        int res = userService.resetPassword(dataMap.get("password"), dataMap.get("mobile"));
        if (res == 0) {
            return BaseReqVo.error(703, "重置数据库密码错误");
        }
        // 返回成功
        return BaseReqVo.ok();
    }

    /**
     * 绑定手机，不用做
     */
    @RequestMapping("bindPhone")
    public BaseReqVo regCaptcha(@RequestBody String encryptedData, @RequestBody String iv) {
        System.out.println("encryptedData = " + encryptedData);
        System.out.println("iv = " + iv);
        return BaseReqVo.ok();
    }

}
