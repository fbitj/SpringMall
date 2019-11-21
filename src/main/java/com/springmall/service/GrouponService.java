package com.springmall.service;

import com.springmall.bean.*;

import java.util.List;

import java.util.List;

public interface GrouponService {
    DataForPage<Groupon_rules> showWholesaleByPage(PageRequest request);

    int deleteRulesById(Groupon_rules rules);

    DataForPage showWholesale(PageRequest request);

    Groupon_rules create(Groupon_rules rules);

    int update(Groupon_rules rules);

    List<GrouponInfo> getGrouponInfo();

    List selectRulesByGoodsId(Integer id);
}
