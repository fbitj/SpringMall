package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.AdRequest;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Topic;
import com.springmall.bean.TopicExample;
import com.springmall.mapper.TopicMapper;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public DataForPage<Topic> showListUserByPage(AdRequest request) {
        PageHelper.startPage(request.getPage(), request.getLimit());

        TopicExample example = new TopicExample();
        if (!StringUtils.isEmpty(request.getTitle())) {
            example.createCriteria().andTitleLike("%" + request.getTitle() + "%");
        }

        if (!StringUtils.isEmpty(request.getSubtitle())) {
            example.createCriteria().andSubtitleLike("%" + request.getSubtitle() + "%");
        }

        List<Topic> topics = topicMapper.selectByExample(example);

        //获取总数
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        long total = pageInfo.getTotal();
        return new DataForPage<Topic>(total,topics);
    }

    /**
     * 新增专题
     * @param topic
     * @return
     */
    @Override
    public Topic addTopic(Topic topic) {
        //添加更新时间
        Date date = new Date();
        topic.setUpdateTime(date);
        topic.setAddTime(date);


        topicMapper.insertSelective(topic);
        return topic;
    }

    /**
     * 更新信息
     * @param topic
     * @return
     */
    @Override
    public Topic updatedTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topicMapper.updateByPrimaryKeySelective(topic);
        return topic;
    }
}
