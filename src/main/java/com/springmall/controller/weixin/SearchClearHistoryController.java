package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import com.springmall.service.SearchHistoryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx")
public class SearchClearHistoryController {
    @Autowired
    SearchHistoryService searchHistoryService;
    @RequestMapping("search/clearhistory")
    public BaseReqVo searchClearHistory(){
        BaseReqVo<Object> objectBaseReqVo = new BaseReqVo<>();
        Subject subject = SecurityUtils.getSubject();
       User user = (User) subject.getPrincipal();
        Integer id = user.getId();
        searchHistoryService.deleteSearchHistory(id);
        objectBaseReqVo.setErrno(0);
        objectBaseReqVo.setErrmsg("成功");
        return objectBaseReqVo;
    }
}
