package com.springmall.service;

import com.springmall.bean.ExpressConfig;

public interface ExpressConfigService {

    ExpressConfig queryExpressConfig();

    int updateExpressConfig(ExpressConfig expressConfig);
}
