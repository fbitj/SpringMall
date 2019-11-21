package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.ExpressInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 团购——物流查询
 */
@RestController
@RequestMapping("wx")
public class WxExpressQuery {
    @RequestMapping("express/query")
    public BaseReqVo<Map<String,Object>> expressQuery(@RequestBody Map<String,String> map){
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        String expCode = map.get("expCode");
        String expNo = map.get("expNo");
        ExpressInfo expressInfo = new ExpressInfo();
        expressInfo.setShipperName(expCode);
        expressInfo.setLogisticCode(expCode);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("expressInfo",expressInfo);
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setData(map1);
       return mapBaseReqVo;
    }
}
