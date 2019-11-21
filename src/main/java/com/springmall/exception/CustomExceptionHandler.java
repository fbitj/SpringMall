package com.springmall.exception;

import com.springmall.bean.BaseReqVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fwj on 2019-11-19.
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public BaseReqVo noPermission() {
//        BaseReqVo fail = BaseReqVo.fail();
//        fail.setErrmsg("没有权限");
        return BaseReqVo.error(700, "没有权限");
    }
}
