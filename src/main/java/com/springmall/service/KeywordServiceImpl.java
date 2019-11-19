package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.Keyword;
import com.springmall.bean.KeywordExample;
import com.springmall.bean.Search_history;
import com.springmall.bean.Search_historyExample;
import com.springmall.mapper.KeywordMapper;
import com.springmall.mapper.Search_historyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    Search_historyMapper search_historyMapper;

    @Override
    public Map<String, Object> queryIssues(Integer page, Integer limit, String keyword, String url) {
        //分页
        PageHelper.startPage(page, limit);
        //查询
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andDeletedEqualTo(false);
        if (keyword != null && url != null) {
            keywordExample.createCriteria().andKeywordLike("%" + keyword + "%").andUrlLike("%" + url + "%");
        }
        if (keyword != null) {
            keywordExample.createCriteria().andKeywordLike("%" + keyword + "%");
        }
        if (url != null) {
            keywordExample.createCriteria().andUrlLike("%" + url + "%");
        }
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>(keywords);
        long total = keywordPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", keywords);
        return map;
    }

    /**
     * 添加关键字
     *
     * @param keyword
     * @return
     */
    @Override
    public Keyword addKeyword(Keyword keyword) {
        Date date = new Date();
        keyword.setAddTime(date);
        keyword.setUpdateTime(date);
        int i = keywordMapper.insertSelective(keyword);
        Integer id = keyword.getId();
        Keyword addKeyword = keywordMapper.selectByPrimaryKey(id);
        return addKeyword;
    }

    /**
     * 编辑
     *
     * @param keyword
     * @return
     */
    @Override
    public Keyword updateKeyword(Keyword keyword) {
        Date date = new Date();
        keyword.setUpdateTime(date);
        int update = keywordMapper.updateByPrimaryKeySelective(keyword);
        Keyword updateKeyword = keywordMapper.selectByPrimaryKey(keyword.getId());
        return updateKeyword;
    }

    /**
     * 删除
     *
     * @param keyword
     * @return
     */
    @Override
    public int deleteKeyword(Keyword keyword) {
        //逻辑删除
        keyword.setDeleted(true);
        Date date = new Date();
        keyword.setUpdateTime(date);
        int delete = keywordMapper.updateByPrimaryKeySelective(keyword);
        //int delete = keywordMapper.deleteByPrimaryKey(keyword.getId());
        return delete;
    }

    /**
     * 微信端搜索关键字
     *
     * @return keywordList
     */
    public List<Keyword> queryKeyWordList() {
        KeywordExample keywordExample = new KeywordExample();
        List<Keyword> keywordList = keywordMapper.selectByExample(keywordExample);
        return keywordList;
    }

    /**
     * 前端搜索历史记录
     *
     * @return searchHistoryList
     */
    public List<Search_history> querySearchHistoryList() {
        Search_historyExample search_historyExample = new Search_historyExample();
        List<Search_history> searchHistoryList = search_historyMapper.selectByExample(search_historyExample);
        return searchHistoryList;
    }

    /**
     * 搜索帮助
     *search/helper
     *@param keyword
     * @return List<Keyword>
     */
    @Override
    public List<Keyword> queryKeyWordList(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andKeywordLike("%" + keyword + "%");
        List<Keyword> keywordList = keywordMapper.selectByExample(keywordExample);
        return keywordList;
    }
}
