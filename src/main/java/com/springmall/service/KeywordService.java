package com.springmall.service;


import com.springmall.bean.Keyword;
import com.springmall.bean.Search_history;

import java.util.List;
import java.util.Map;

public interface KeywordService {
    Map<String, Object> queryIssues(Integer page, Integer limit, String keyword, String url);
    Keyword addKeyword(Keyword keyword);
    Keyword updateKeyword(Keyword keyword);
    int deleteKeyword(Keyword keyword);
    List<Keyword> queryKeyWordList();
    List<Search_history> querySearchHistoryList();
    List<Keyword> queryKeyWordList(String keyword);
}
