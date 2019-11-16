package com.springmall.service;

import com.springmall.bean.WxConfig;

public interface WxConfigService {

    WxConfig queryWxConfig();

    int updateWxConfig(WxConfig wxConfig);
}
