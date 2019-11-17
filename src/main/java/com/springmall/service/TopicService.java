package com.springmall.service;

import com.springmall.bean.AdRequest;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Topic;

public interface TopicService {
    DataForPage<Topic> showListUserByPage(AdRequest request);

    Topic addTopic(Topic topic);

    Topic updatedTopic(Topic topic);
}
