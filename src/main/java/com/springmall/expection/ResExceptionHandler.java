package com.springmall.expection;

import com.springmall.bean.BaseReqVo;
import com.springmall.exception.OrderException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ResExceptionHandler {

    /**
     * 处理接收json格式请求时数据转换异常
     * @param exception
     * @return
     */
    /*@ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseReqVo handleCustomException(HttpMessageNotReadableException exception){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(402);
        baseReqVo.setErrmsg("参数值不对");
        return baseReqVo;
    }*/
 /*   @ExceptionHandler(Exception.class)
    public BaseReqVo handleCustomException(Exception exception){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(500);
        baseReqVo.setErrmsg("服务器异常请稍后再试");
        return baseReqVo;
    }

    @ExceptionHandler(OrderException.class)
    public BaseReqVo handlerOrderException(Exception exception){
        return BaseReqVo.error(500,exception.getMessage());
    }*/
}
