package com.springmall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmall.bean.Feedback;
import com.springmall.bean.FeedbackExample;
import com.springmall.bean.User;
import com.springmall.mapper.FeedbackMapper;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理——意见反馈——service层
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> queryFeedBackList(Integer id, String username) {
        FeedbackExample feedbackExample = new FeedbackExample();
        List<Feedback> feedbackList = null;
        ObjectMapper objectMapper=new ObjectMapper();
        if (id == null && username == null) {
            feedbackList = feedbackMapper.selectByExample(feedbackExample);
        } else if (id != null && username == null) {
            feedbackExample.createCriteria().andIdEqualTo(id);
            feedbackList = feedbackMapper.selectByExample(feedbackExample);
        } else if (username != null && id == null) {
            feedbackExample.createCriteria().andUsernameLike("%" + username + "%");
            feedbackList = feedbackMapper.selectByExample(feedbackExample);
        } else {
            feedbackExample.createCriteria().andUsernameLike("%" + username + "%").andIdEqualTo(id);
            feedbackList = feedbackMapper.selectByExample(feedbackExample);
        }
/*        for (Feedback feedback : feedbackList) {
            try {
                String[] urls= objectMapper.readValue(feedback.getPicUrls(), String[].class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }*/
        return feedbackList;
    }

    /**
     * 新增反馈
     * @param feedback
     * @return
     */
    @Override
    public int submitFeedBack(Feedback feedback) {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if (principal != null) {
            feedback.setUserId(principal.getId());
            feedback.setUsername(principal.getUsername());
        }
        return feedbackMapper.insertSelective(feedback);
    }
}
