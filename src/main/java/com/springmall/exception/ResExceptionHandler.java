package com.springmall.exception;

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
/*    @ExceptionHandler(Exception.class)
    public BaseReqVo handleCustomException(Exception exception){
        exception.printStackTrace();
        return BaseReqVo.error(500,"网络繁忙，请稍后再试！");
    }

    @ExceptionHandler(OrderException.class)
    public BaseReqVo handlerOrderException(Exception exception){
        exception.printStackTrace();
        return BaseReqVo.error(500,exception.getMessage());
    }*/
}
