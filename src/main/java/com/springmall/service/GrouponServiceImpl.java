package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.GrouponMapper;
import com.springmall.mapper.Groupon_rulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public DataForPage<Groupon_rules> showWholesaleByPage(PageRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());

        //判断是否有goodsId
        Integer goodsId = request.getGoodsId();
        Groupon_rulesExample example = new Groupon_rulesExample();
        Groupon_rulesExample.Criteria criteria = example.createCriteria();
        if (goodsId != null) {
            criteria.andGoodsIdEqualTo(goodsId);
        }

        criteria.andDeletedEqualTo(false);
        List<Groupon_rules> rules = grouponRulesMapper.selectByExample(example);
        PageInfo<Groupon_rules> pageInfo = new PageInfo<>(rules);
        return new DataForPage<>(pageInfo.getTotal(), rules);
    }

    /**
     * 删除某条团购规则
     * @param rules
     * @return
     */
    @Override
    public int deleteRulesById(Groupon_rules rules) {
        return grouponRulesMapper.deleteById(rules.getId());
    }

    /**
     * 显示团购活动
     * @param request
     * @return
     */
    @Override
    public DataForPage showWholesale(PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getLimit());

        List<GrouponResult> result = grouponMapper.selectWholesale(request);
        /*//sub暂定
        for (GrouponResult grouponResult : result) {
            grouponResult.setSubGroupons(new ArrayList());
        }*/
        PageInfo<GrouponResult> info = new PageInfo<>(result);
        return new DataForPage(info.getTotal(), result);
    }

    /**
     * 创建团购
     * @param rules
     * @return
     */
    @Override
    public Groupon_rules create(Groupon_rules rules) {
        Date date = new Date();
        rules.setAddTime(date);
        rules.setUpdateTime(date);
        grouponRulesMapper.insertSelective(rules);
        return rules;
    }

    /**
     * 更新团购
     * @param rules
     * @return
     */
    @Override
    public int update(Groupon_rules rules) {
        rules.setUpdateTime(new Date());
        return grouponRulesMapper.updateByPrimaryKeySelective(rules);
    }

    @Override
    public List<GrouponInfo> getGrouponInfo() {
        return grouponRulesMapper.getGrouponInfo();
    }
    /**
     * 根据商品id查询该商品参加的团购
     * @param id
     * @return
     */
    @Override
    public List selectRulesByGoodsId(Integer id) {
        Groupon_rulesExample example = new Groupon_rulesExample();
        example.createCriteria().andGoodsIdEqualTo(id).andDeletedEqualTo(false);
        return grouponRulesMapper.selectByExample(example);
    }
}
