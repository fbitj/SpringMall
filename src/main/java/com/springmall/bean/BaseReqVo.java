package com.springmall.bean;

import lombok.Data;

@Data
public class BaseReqVo<T> {
    T data;
    String errmsg;
    int errno;

    public BaseReqVo() {
    }

    public BaseReqVo(T data, String errmsg, int errno) {
        this.data = data;
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public static BaseReqVo faild(String errmsg){
        BaseReqVo baseRespVo = new BaseReqVo();
        baseRespVo.setErrmsg(errmsg);
        baseRespVo.setErrno(500);
        return baseRespVo;
    }
}
