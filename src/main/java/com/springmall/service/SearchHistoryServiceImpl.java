package com.springmall.service;

import com.springmall.bean.Search_history;
import com.springmall.bean.Search_historyExample;
import com.springmall.exception.DbException;
import com.springmall.mapper.Search_historyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户管理-搜索历史-service层
 */
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {
    @Autowired
    Search_historyMapper search_historyMapper;

    @Override
    public List<Search_history> querySearchHistoryList(Integer userId, String keyword) {
        Search_historyExample search_historyExample = new Search_historyExample();
        List<Search_history> searchHistoryList = null;
        if (userId == null && keyword == null) {
            searchHistoryList=search_historyMapper.selectByExample(search_historyExample);
        }else if(userId != null && keyword ==null){
            search_historyExample.createCriteria().andUserIdEqualTo(userId);
            searchHistoryList=search_historyMapper.selectByExample(search_historyExample);
        }else if(keyword != null && userId == null){
            search_historyExample.createCriteria().andKeywordLike("%"+keyword+"%");
            searchHistoryList=search_historyMapper.selectByExample(search_historyExample);
        }else {
            search_historyExample.createCriteria().andUserIdEqualTo(userId).andKeywordLike("%"+keyword+"%");
            searchHistoryList=search_historyMapper.selectByExample(search_historyExample);
        }
        return searchHistoryList;
    }

    /**
     * 刪除搜索的歷史記錄
     */
    @Override
    public void deleteSearchHistory(Integer userId) {
        Search_historyExample search_historyExample = new Search_historyExample();
        search_historyExample.createCriteria().andUserIdEqualTo(userId);
        int i = search_historyMapper.deleteByExample(search_historyExample);
    }

    /**
     * 添加用户搜索历史
     * @param userId
     * @param keyword
     * @return
     */
    @Override
    public int addUserSearchHistory(Integer userId, String keyword) {
        Search_history history = new Search_history();
        //如果用户搜索过该关键词则直接更新
        Search_historyExample example = new Search_historyExample();
        example.createCriteria().andUserIdEqualTo(userId).andKeywordEqualTo(keyword);
        List<Search_history> searchHistories = search_historyMapper.selectByExample(example);
        //Date updateTime = new Date();
        if (searchHistories.size() > 0) {
            //说明用户搜索过该关键词
            Search_history record = new Search_history();
            //record.setUpdateTime(updateTime);
            int update = search_historyMapper.updateByExampleSelective(record, example);
            if (update == 0) throw new DbException();
            return update;
        }
        history.setUserId(userId);
        history.setKeyword(keyword);
        history.setFrom("wx");
        /*history.setAddTime(updateTime);
        history.setAddTime(updateTime);*/
        int i = search_historyMapper.insertSelective(history);
        if (i == 0) throw new DbException();
        return i;
    }
}
