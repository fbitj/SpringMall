package com.springmall.expection;

import com.springmall.bean.BaseRespVo;
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
    public BaseRespVo handleCustomException(HttpMessageNotReadableException exception){
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setErrno(402);
        BaseRespVo.setErrmsg("参数值不对");
        return BaseRespVo;
    }*/

}
