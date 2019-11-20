package com.springmall.service;

import com.springmall.bean.Search_history;

import java.util.List;

public interface SearchHistoryService {
    List<Search_history> querySearchHistoryList(Integer userId,String keyword);
    //删除搜索的历史记录
    void deleteSearchHistory();
}
