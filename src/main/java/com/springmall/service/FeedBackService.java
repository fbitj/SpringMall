package com.springmall.service;

import com.springmall.bean.Feedback;

import java.util.List;

public interface FeedBackService {
    List<Feedback> queryFeedBackList(Integer id,String username);
}
