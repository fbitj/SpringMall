package com.springmall.service;

import com.springmall.bean.*;

import java.util.HashMap;
import java.util.List;

public interface GrouponService {

    DataForPage<Groupon_rules> showWholesaleByPage(PageRequest request);

    int deleteRulesById(Groupon_rules rules);

    DataForPage showWholesale(PageRequest request);

    Groupon_rules create(Groupon_rules rules);

    int update(Groupon_rules rules);

    HashMap<String, Object> queryGrouponListByPage(String page, String size);

    HashMap<String, Object> queryMyGrouponByShowType(String showType, int userId);

    GrouponDetail queryGrouponByGrouponId(int grouponId);

    List<GrouponInfo> getGrouponInfo();

    List selectRulesByGoodsId(Integer id);

    /**
     * 发起团购
     * @param userid 发起用户
     * @param orderId 订单id
     * @param grouponRulesId 团购规则id
     * @return
     */
    int startGroupon(int userid, Integer orderId, int grouponRulesId);

}
