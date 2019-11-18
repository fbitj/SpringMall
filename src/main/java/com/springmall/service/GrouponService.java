package com.springmall.service;

import com.springmall.bean.PageRequest;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Groupon_rules;

public interface GrouponService {
    DataForPage<Groupon_rules> showWholesaleByPage(PageRequest request);

    int deleteRulesById(Groupon_rules rules);

    DataForPage showWholesale(PageRequest request);
}
