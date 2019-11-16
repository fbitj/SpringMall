package com.springmall.utils;

import com.springmall.bean.BaseReqVo;

public class ResultUtil {

    public static BaseReqVo success(Object data) {
        BaseReqVo reqVo = new BaseReqVo();
        reqVo.setErrmsg("成功");
        reqVo.setErrno(0);
        reqVo.setData(data);
        return reqVo;
    }
}
