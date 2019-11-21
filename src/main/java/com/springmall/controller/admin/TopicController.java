package com.springmall.controller.admin;

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
        String illegal = isIllegal(topic);
        if (illegal != null) {
            return ResultUtil.fail(402, illegal);
        }        Topic result = topicService.addTopic(topic);
        return ResultUtil.success(result);
    }

    /**
     * 更新专题
     * @param topic
     * @return
     */
    @RequestMapping("update")
    public BaseReqVo updateTopicById(@RequestBody Topic topic) {
        String illegal = isIllegal(topic);
        if (illegal != null) {
            return ResultUtil.fail(402, illegal);
        }
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

    private String isIllegal(Topic topic) {
        if (topic.getPrice() == null || topic.getPrice().compareTo(BigDecimal.ZERO) == -1) {
            return "底价必须大于等于0";
        }
        String readCount = topic.getReadCount();
        if (readCount != null) {
            if (readCount.toLowerCase().endsWith("k")) {
                String substring = readCount.substring(0, readCount.length() - 1);
                try {
                    //没有异常则表示格式正确
                    Double.parseDouble(substring.trim());
                    return null;
                } catch (NumberFormatException e) {
                    return "格式有误,单位必须为'k'(示例: 1000k,1000.5k)";
                }
            } else {
                return "格式有误,单位必须为'k'(示例: 1000k,1000.5k)";
            }

        }
        return null;
    }
}
