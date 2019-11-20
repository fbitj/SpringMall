package com.springmall.utils;

import com.springmall.bean.BaseRespVo;

public class ResultUtil {

    /**
     * 封装成功返回时的json对象
     * @param data
     * @return
     */
    public static BaseRespVo success(Object data) {
        BaseRespVo reqVo = new BaseRespVo();
        reqVo.setErrmsg("成功");
        reqVo.setErrno(0);
        reqVo.setData(data);
        return reqVo;
    }

    /**
     * 封装异常时返回的json对象
     * @param status
     * @param message
     * @return
     */
    public static BaseRespVo fail(int status, String message) {
        BaseRespVo reqVo = new BaseRespVo();
        reqVo.setErrno(status);
        reqVo.setErrmsg(message);
        return reqVo;
    }
}
