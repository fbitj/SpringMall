package com.springmall.bean;

import lombok.Data;

@Data
public class BaseRespVo<T> {
    T data;
    String errmsg;
    int errno;

    public BaseRespVo() {
    }

    public BaseRespVo(T data, String errmsg, int errno) {
        this.data = data;
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public static BaseRespVo ok(){
        BaseRespVo tBaseRespVo = new BaseRespVo<>();
        tBaseRespVo.setErrmsg("成功");
        tBaseRespVo.setErrno(0);
        return tBaseRespVo;
    }
    public static BaseRespVo failed(String errmsg, int errno){
        BaseRespVo tBaseRespVo = new BaseRespVo<>();
        tBaseRespVo.setErrmsg(errmsg);
        tBaseRespVo.setErrno(errno);
        return tBaseRespVo;
    }
}
