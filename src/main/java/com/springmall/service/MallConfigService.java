package com.springmall.service;

import com.springmall.bean.MallConfig;

public interface MallConfigService {

    MallConfig queryMallConfig ();

    int updateMallConfig (MallConfig mallConfig);
}
