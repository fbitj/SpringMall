package com.springmall.controller;

import com.springmall.bean.AdRequest;
import com.springmall.bean.BaseReqVo;
import com.springmall.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/ad")
public class AdvertiseController {

    @Autowired
    AdvertiseService advertiseService;

    /**
     * 获取所有广告信息并分页显示
     * @param adRequest
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo<Map<String,Object>> advertiseList(AdRequest adRequest) {
        Map<String,Object> advertises = advertiseService.totalAdvertise(adRequest);

        BaseReqVo<Map<String,Object>> reqVo = new BaseReqVo<>();
        reqVo.setData(advertises);
        reqVo.setErrno(0);
        reqVo.setErrmsg("成功");
        return reqVo;
    }
}
