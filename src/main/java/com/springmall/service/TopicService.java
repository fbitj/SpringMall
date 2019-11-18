package com.springmall.service;

import com.springmall.bean.PageRequest;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Topic;

public interface TopicService {
    DataForPage<Topic> showListUserByPage(PageRequest request);

    Topic addTopic(Topic topic);

    Topic updatedTopic(Topic topic);

    int deleteTopicById(Topic topic);
}
