package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.GrouponMapper;
import com.springmall.mapper.Groupon_rulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    Groupon_rulesMapper grouponRulesMapper;

    /**
     * 获取团购规则信息并分页
     * @param request
     * @return
     */
    @Override
    public DataForPage<Groupon_rules> showWholesaleByPage(AdRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());

        //判断是否有goodsId
        Integer goodsId = request.getGoodsId();
        Groupon_rulesExample example = new Groupon_rulesExample();
        if (goodsId != null) {
            example.createCriteria().andGoodsIdEqualTo(goodsId);
        }

        List<Groupon_rules> rules = grouponRulesMapper.selectByExample(example);
        PageInfo<Groupon_rules> pageInfo = new PageInfo<>(rules);
        return new DataForPage<>(pageInfo.getTotal(), rules);
    }
}
