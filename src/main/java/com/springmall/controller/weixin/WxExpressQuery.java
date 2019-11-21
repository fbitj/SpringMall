package com.springmall.controller.weixin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.ExpressInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 团购——物流查询
 */
@RestController
@RequestMapping("wx")
public class WxExpressQuery {
    @RequestMapping("express/query")
    public BaseReqVo expressQuery(@RequestBody Map<String,String> map){
        //BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        String expCode = map.get("expCode");
        String expNo = map.get("expNo");
        //ExpressInfo expressInfo = new ExpressInfo();

       /* expressInfo.setShipperName(expCode);
        expressInfo.setLogisticCode(expNo);
        expressInfo.setTraces("湖北顺丰快递总仓");*//*
        HashMap<String, Object> map1 = new HashMap<>();
        //expressInfo
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("shipperName",expCode);
        map3.put("logisticCode",expNo);
        map3.put("Traces","湖北顺丰快递总仓");
        //iitem
        HashMap<String, Object> map2 = new HashMap<>();
        //揽件时间
        Date acceptTime= new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatTime = simpleDateFormat.format(acceptTime);
        //map1.put("expressInfo",expressInfo);
        map1.put("expressInfo",map3);
        map2.put("AcceptStation","武汉市顺丰快递站点");
        map2.put("AcceptTime",formatTime);
        map1.put("iitem",map2);
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setData(map1);*/
        HashMap<String, String> expressInfo = new HashMap<>();
        HashMap<String, Object> resmap = new HashMap<>();
        expressInfo.put("shipperName",expCode);
        expressInfo.put("logisticCode",expNo);
        resmap.put("expressInfo",expressInfo);
        return BaseReqVo.ok(expressInfo);
    }
}
