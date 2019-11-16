package com.springmall.service;

import com.springmall.bean.OrderConfig;
import com.springmall.bean.OrderConfigExample;
import com.springmall.mapper.OrderConfigMapper;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderConfigServiceImpl implements OrderConfigService {

    @Autowired
    OrderConfigMapper orderConfigMapper;

    /**
     * 获取订单配置信息
     * @return 现订单配置信息对象
     */
    @Override
    public OrderConfig queryOrderConfig() {
        OrderConfigExample orderConfigExample = new OrderConfigExample();
        return orderConfigMapper.selectByExample(orderConfigExample).get(0);
    }

    /**
     * 更新订单配置信息
     * @param orderConfig
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整，
     * 3表示确认收货后超期天数不为正数，
     * 4表示用户下单后超时分钟数不为正数，
     * 5表示订单发货后超期天数不为正数，
     * 6表示修改参数中存在非数字。
     */
    @Override
    public int updateOrderConfig(OrderConfig orderConfig) {
        if (StringUtils.isEmpty(orderConfig.getLitemall_order_comment()) ||
            StringUtils.isEmpty(orderConfig.getLitemall_order_unpaid()) ||
            StringUtils.isEmpty(orderConfig.getLitemall_order_unconfirm())
        ){    //修改条件不完整
            return 2;
        }
        try{
            if (Integer.parseInt(orderConfig.getLitemall_order_comment())<=0){    //确认收货后超期天数需大于0
                return 3;
            }
            if (Integer.parseInt(orderConfig.getLitemall_order_unpaid())<=0){    //用户下单后超时分钟数需大于0
                return 4;
            }
            if (Integer.parseInt(orderConfig.getLitemall_order_unconfirm())<=0){    //订单发货后超期天数需大于0
                return 5;
            }
        }catch (Exception e){
            return 6;
        }
        OrderConfig updateOrderConfig = new OrderConfig(1,orderConfig.getLitemall_order_comment(),orderConfig.getLitemall_order_unpaid(),orderConfig.getLitemall_order_unconfirm());
        OrderConfigExample orderConfigExample = new OrderConfigExample();
        int result = 0;
        if(orderConfigMapper.selectByExample(orderConfigExample).size()==0){    //表中无订单配置相关数据，插入一条配置信息。
             result = orderConfigMapper.insertSelective(updateOrderConfig);
            return result;
        }
        result = orderConfigMapper.updateByPrimaryKeySelective(updateOrderConfig);    //表中有订单配置相关数据，更新该条配置信息。
        return result;
    }
}
