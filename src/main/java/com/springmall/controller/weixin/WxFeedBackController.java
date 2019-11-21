package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Feedback;
import com.springmall.service.FeedBackService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/feedback")
public class WxFeedBackController {

    @Autowired
    FeedBackService feedBackService;

    @RequestMapping("submit")
    public BaseReqVo submitFeedBack(@RequestBody Feedback feedback) {
        int i = feedBackService.submitFeedBack(feedback);
        if (i == 0) {
            //异常
            return ResultUtil.fail(402, "请登陆后重试");
        }
        return ResultUtil.success(null);
    }
}
