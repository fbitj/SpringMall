package com.springmall.service;

import com.springmall.bean.AdRequest;
import com.springmall.bean.DataForPage;
import com.springmall.bean.Groupon;
import com.springmall.bean.Groupon_rules;

public interface GrouponService {
    DataForPage<Groupon_rules> showWholesaleByPage(AdRequest request);
}
