package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.*;
import com.springmall.utils.HandleOptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    Groupon_rulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderStatusMapper orderStatusMapper;
    @Autowired
    Order_goodsMapper orderGoodsMapper;
    @Autowired
    Goods_specificationMapper goodsSpecificationMapper;

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

    /**
     * 分页显示团购信息
     * @param page
     * @param size
     * @return 当前页团购信息列表与列表长度的封装Map
     */
    @Override
    public HashMap<String, Object> queryGrouponListByPage(String page, String size) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andDeletedEqualTo(false);    //需过滤逻辑删除
        List<Groupon> grouponList = grouponMapper.selectByExample(grouponExample);
        for (Groupon groupon : grouponList) {
            HashMap<String, Object> data = new HashMap<>();
            Groupon_rules rules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
            Goods goods = goodsMapper.selectByPrimaryKey(rules.getGoodsId());
            BigDecimal groupon_price = goods.getRetailPrice().subtract(rules.getDiscount());    //团购价格为商品零售价减团购优惠金额
            data.put("groupon_price",groupon_price);
            data.put("goods",goods);
            data.put("groupon_member",rules.getDiscountMember());
        }
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("data",grouponList);
        dataMap.put("count",grouponList.size());
        return dataMap;
    }

    /**
     * 根据参团角色获取登录用户的团购信息
     * @param showType
     * @param userId
     * @return 当前登录用户对应参团角色的团购信息列表与列表长度的封装Map
     */
    @Override
    public HashMap<String, Object> queryMyGrouponByShowType(String showType, int userId) {
        List<GrouponDetail> grouponDetailList = new ArrayList<>();
        GrouponExample grouponExample = new GrouponExample();
        GrouponExample.Criteria criteria = grouponExample.createCriteria().andDeletedEqualTo(false);    //过滤逻辑删除
        Boolean isCreator = null;
        if ("0".equals(showType)){
            isCreator=true;
            criteria.andCreatorUserIdEqualTo(userId);    //当前登录用户为团购发起者
        }
        if ("1".equals(showType)){
            isCreator=false;
            criteria.andCreatorUserIdNotEqualTo(userId).andUserIdEqualTo(userId);    //当前登录用户非团购发起者
        }
        List<Groupon> grouponList = grouponMapper.selectByExample(grouponExample);
        for (Groupon groupon : grouponList) {
            GrouponDetail grouponDetail = new GrouponDetail();
            Order order = orderMapper.selectByPrimaryKey(groupon.getOrderId());
            User creator = userMapper.selectByPrimaryKey(groupon.getCreatorUserId());
            GrouponExample grouponExample1 = new GrouponExample();
            grouponExample1.createCriteria().andGrouponIdEqualTo(groupon.getGrouponId()).andDeletedEqualTo(false);    //同一个grouponId下的User即为该团购成员，需过滤逻辑删除。
            long joinerCount = grouponMapper.countByExample(grouponExample1);
            OrderStatusExample orderStatusExample = new OrderStatusExample();
            orderStatusExample.createCriteria().andStatusIdEqualTo(Integer.valueOf(order.getOrderStatus()));
            OrderStatus orderStatus = orderStatusMapper.selectByExample(orderStatusExample).get(0);
            Groupon_rules rules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
            Order_goodsExample orderGoodsExample = new Order_goodsExample();
            orderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId()).andDeletedEqualTo(false);    //同一个orderId的OrderGoods即为一个订单下的商品，需过滤逻辑删除。
            List<Order_goods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);    //多件商品共用一个订单，其中有一者参团，需显示该订单下所有的商品信息。
            HandleOption handleOption = HandleOptionUtil.handleOption(order.getOrderStatus());
            grouponDetail.setOrderStatusText(orderStatus.getStatusText());
            grouponDetail.setCreator(creator.getNickname());
            grouponDetail.setGroupon(groupon);
            grouponDetail.setOrderId(order.getId());
            grouponDetail.setOrderSn(order.getOrderSn());
            grouponDetail.setActualPrice(order.getActualPrice());
            grouponDetail.setJoinerCount((int)joinerCount);
            grouponDetail.setGoodsList(orderGoodsList);
            grouponDetail.setRules(rules);
            grouponDetail.setId(groupon.getId());
            grouponDetail.setIsCreator(isCreator);
            grouponDetail.setHandleOption(handleOption);
            grouponDetailList.add(grouponDetail);
        }
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("data",grouponDetailList);
        dataMap.put("count",grouponDetailList.size());
        return dataMap;
    }

    /**
     * 获取当前登录用户指定团购表id的团购详情
     * @param grouponId
     * @return
     */
    @Override
    public GrouponDetail queryGrouponByGrouponId(int grouponId) {
        GrouponDetail grouponDetail = new GrouponDetail();
        Groupon groupon = grouponMapper.selectByPrimaryKey(grouponId);
        User creator = userMapper.selectByPrimaryKey(groupon.getCreatorUserId());
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andGrouponIdEqualTo(groupon.getGrouponId()).andDeletedEqualTo(false);    //过滤逻辑删除
        List<Groupon> grouponList = grouponMapper.selectByExample(grouponExample);
        List<User> joiners = new ArrayList<>();
        for (Groupon g : grouponList) {
            User user = userMapper.selectByPrimaryKey(g.getUserId());
            joiners.add(user);
        }
        Order order = orderMapper.selectByPrimaryKey(groupon.getOrderId());
        OrderStatusExample orderStatusExample = new OrderStatusExample();
        orderStatusExample.createCriteria().andStatusIdEqualTo(Integer.valueOf(order.getOrderStatus()));
        OrderStatus orderStatus = orderStatusMapper.selectByExample(orderStatusExample).get(0);
        order.setOrderStatusText(orderStatus.getStatusText());
        HandleOption handleOption = HandleOptionUtil.handleOption(order.getOrderStatus());
        order.setHandleOption(handleOption);
        Order_goodsExample orderGoodsExample = new Order_goodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId()).andDeletedEqualTo(false);    //过滤逻辑删除
        List<Order_goods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);
        for (Order_goods orderGoods : orderGoodsList) {
            Goods_specificationExample goodsSpecificationExample = new Goods_specificationExample();
            goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(orderGoods.getGoodsId()).andDeletedEqualTo(false);    //过滤逻辑删除
            List<Goods_specification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
            List<String> goodsSpecificationValueList = new ArrayList<>();
            for (Goods_specification goodsSpecification : goodsSpecificationList) {
                goodsSpecificationValueList.add(goodsSpecification.getValue());
            }
            orderGoods.setGoodsSpecificationValues(goodsSpecificationValueList);
            Goods goods = goodsMapper.selectByPrimaryKey(orderGoods.getGoodsId());
            orderGoods.setRetailPrice(goods.getRetailPrice());
        }
        Groupon_rules grouponRules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
        grouponDetail.setCreator(creator);
        grouponDetail.setGroupon(groupon);
        grouponDetail.setJoiners(joiners);
        grouponDetail.setOrderInfo(order);
        grouponDetail.setOrderGoods(orderGoodsList);
        grouponDetail.setRules(grouponRules);
        grouponDetail.setLinkGrouponId(groupon.getGrouponId());
        return grouponDetail;
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
