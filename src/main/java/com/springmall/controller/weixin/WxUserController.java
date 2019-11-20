package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fwj on 2019-11-20.
 */
@RestController
@RequestMapping("wx/user")
public class WxUserController {

    @RequestMapping("index")
    public BaseReqVo index() {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();

        return BaseReqVo.ok();

    }
}
