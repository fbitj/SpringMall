package com.springmall.service;

import com.springmall.bean.Goods;
import com.springmall.bean.PageRequest;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Groupon_rules;

import java.util.List;

public interface GrouponService {
    DataForPage<Groupon_rules> showWholesaleByPage(PageRequest request);

    int deleteRulesById(Groupon_rules rules);

    DataForPage showWholesale(PageRequest request);

    Groupon_rules create(Groupon_rules rules);

    int update(Groupon_rules rules);

    List selectRulesByGoodsId(Integer id);
}
