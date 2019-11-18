package com.springmall.controller;

import com.springmall.bean.BaseReqVo;
import com.springmall.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomePageController {
    @Autowired
    HomePageService homePageService;
    /**
     * 首页
     * @return
     */
    @RequestMapping("admin/dashboard")
    public BaseReqVo homePage() {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Long> map = homePageService.dataTotal();
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
