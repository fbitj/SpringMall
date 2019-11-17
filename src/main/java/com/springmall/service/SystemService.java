package com.springmall.service;

import com.springmall.bean.ExpressConfig;
import com.springmall.bean.MallConfig;
import com.springmall.bean.OrderConfig;
import com.springmall.bean.WxConfig;

import java.util.List;
import java.util.Map;

public interface SystemService {

    MallConfig queryMallConfig ();

    int updateMallConfig (List<Map<String,String>> paramMapList);

    ExpressConfig queryExpressConfig();

    int updateExpressConfig(List<Map<String,String>> paramMapList);

    OrderConfig queryOrderConfig();

    int updateOrderConfig(List<Map<String,String>> paramMapList);

    WxConfig queryWxConfig();

    int updateWxConfig(List<Map<String,String>> paramMapList);
}
