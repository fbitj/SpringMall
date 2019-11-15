package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.Goods;
import com.springmall.bean.GoodsExample;
import com.springmall.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> queryGoodsByPage(String page, String limit, String goodsSn, String name, String sortField, String order) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(sortField + " " + order);
        // goodsSn或name不为空是才查询
        if (!StringUtils.isEmpty(goodsSn)) {
            goodsExample.createCriteria().andGoodsSnEqualTo(goodsSn);
        }
        if (!StringUtils.isEmpty(name)) {
            goodsExample.createCriteria().andNameLike("%" + name + "%");
        }
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
//        PageInfo<Goods> goodsInfo = new PageInfo<>(goods);
//        long total = goodsInfo.getTotal();
        return goods;
    }
}
