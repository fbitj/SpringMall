package com.springmall.mapper;

import com.springmall.bean.GoodsStat;
import com.springmall.bean.Order_goods;
import com.springmall.bean.Order_goodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Order_goodsMapper {
    long countByExample(Order_goodsExample example);

    int deleteByExample(Order_goodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order_goods record);

    int insertSelective(Order_goods record);

    List<Order_goods> selectByExample(Order_goodsExample example);

    Order_goods selectByPrimaryKey(Integer id);

    Order_goods selectByPrimaryKey2(Integer orderId);

    int updateByExampleSelective(@Param("record") Order_goods record, @Param("example") Order_goodsExample example);

    int updateByExample(@Param("record") Order_goods record, @Param("example") Order_goodsExample example);

    int updateByPrimaryKeySelective(Order_goods record);

    int updateByPrimaryKey(Order_goods record);
    // 获取订单与商品的统计数据
    List<GoodsStat> selectGoodsStat();
}

