package com.springmall.controller;

import com.springmall.bean.*;
import com.springmall.service.TopicService;
import com.springmall.utils.ResultUtil;
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
    public BaseReqVo showTopicByPage(PageRequest request) {
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
        //底价不能为null且必须大于等于0
        if (isIllegal(topic.getPrice())) return ResultUtil.fail(402, "底价必须大于等于0");
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
        if (isIllegal(topic.getPrice())) return ResultUtil.fail(402, "底价必须大于等于0");
        Topic result = topicService.updatedTopic(topic);
        return ResultUtil.success(result);
    }

    /**
     * 删除某条专题信息
     * @param topic
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteTopicById(@RequestBody Topic topic) {
        topicService.deleteTopicById(topic);
        return ResultUtil.success(null);
    }

    private boolean isIllegal(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) == -1) {
            return true;
        }
        return false;
    }
}
