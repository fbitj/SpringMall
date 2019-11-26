package com.springmall.expection;

import com.springmall.bean.BaseRespVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ResExceptionHandler {
    /*@ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseRespVo handleCustomException(HttpMessageNotReadableException exception){
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setErrno(402);
        BaseRespVo.setErrmsg("参数值不对");
        return BaseRespVo;
    }*/
    @ExceptionHandler(AuthorizationException.class)
    public BaseRespVo noPermission() {
        return BaseRespVo.failed("没有权限,请联系管理员", 605);
    }
}
