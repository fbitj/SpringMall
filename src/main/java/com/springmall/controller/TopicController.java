package com.springmall.controller;

import com.springmall.bean.AdRequest;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Topic;
import com.springmall.service.TopicService;
import com.springmall.utils.ResultUtil;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("admin/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    /**
     * 根据条件显示专题信息并分页
     * @param request
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo showTopicByPage(AdRequest request) {
        DataForPage<Topic> result = topicService.showListUserByPage(request);
        return ResultUtil.success(result);
    }

    /**
     * 新增专题
     * @param topic
     * @return
     */
    @RequestMapping("create")
    public BaseReqVo addTopic(@RequestBody Topic topic) {
        //底价不能为null
        if (topic.getPrice() == null) return ResultUtil.fail(402, "参数不对");
        Topic result = topicService.addTopic(topic);
        return ResultUtil.success(result);
    }

    /**
     * 更新专题
     * @param topic
     * @return
     */
    @RequestMapping("update")
    public BaseReqVo updateTopicById(@RequestBody Topic topic) {
        if (topic.getPrice() == null) return ResultUtil.fail(402, "参数不对");
        Topic result = topicService.updatedTopic(topic);
        return ResultUtil.success(result);
    }
}
