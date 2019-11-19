package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import com.springmall.bean.UserInfo;
import com.springmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by fwj on 2019-11-19.
 */

@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    UserService userService;

    /**
     * 微信端登录
     * @return
     * {
     *     "errno": 0,
     *     "data": {
     *         "userInfo": {
     *             "nickName": "user2",
     *             "avatarUrl": ""
     *         },
     *         "tokenExpire": "2019-11-20T03:29:41.211",
     *         "token": "v1wwyx0mgrjfv6rmqqeuswdr4ynojs1j"
     *     },
     *     "errmsg": "成功"
     * }
     *
     * {"errno":700,"errmsg":"账号密码不对"}
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseReqVo login(@RequestBody User user) {
        int res = userService.userLogin(user);
        if (res == 2) {
            return BaseReqVo.error(700, "账号密码不对");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getUsername());
        userInfo.setAvatarUrl("");
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("userInfo", userInfo);
        userData.put("tokenExpire", "2019-11-26T04:31:03.123");
        userData.put("token", "2it60277eyre0735kqklx10huy9h8ddm");
        return BaseReqVo.ok(userData);
    }
}
