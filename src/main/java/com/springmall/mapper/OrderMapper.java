package com.springmall.mapper;

import com.springmall.bean.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderStat> selectOrderStat();

    /**
     * 查询未删除订单
     *
     * @param id
     * @return
     */
    OrderRespVo queryOrderById(@Param("id") int id);

    int updateOrderDeletedAndTimeById(@Param("id") Integer id);

    int OrderPayById(@Param("id") int id, @Param("status") short status, @Param("payId") String payId);
}