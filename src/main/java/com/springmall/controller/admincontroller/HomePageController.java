package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
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
    public BaseRespVo homePage() {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Map<String, Long> map = homePageService.dataTotal();
        BaseRespVo.setData(map);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }
}
