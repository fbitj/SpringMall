package com.springmall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Feedback;
import com.springmall.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理——意见反馈——controller层
 */
@Controller
@RequestMapping("admin")
public class FeedBackController {
    @Autowired
    FeedBackService feedBackService;

    @RequestMapping("feedback/list")
    @ResponseBody
    public BaseReqVo<Map<String, Object>> getFeedBackList(Integer page, Integer limit, String sort, String order, Integer id, String username) {
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        List<Feedback> feedbackList = feedBackService.queryFeedBackList(id, username);
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbackList);
        long total = feedbackPageInfo.getTotal();
        map.put("total", total);
        map.put("items", feedbackList);
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }
}
