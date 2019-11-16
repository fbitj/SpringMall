package com.springmall.mapper;

import com.springmall.bean.OrderConfig;
import com.springmall.bean.OrderConfigExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderConfigMapper {
    long countByExample(OrderConfigExample example);

    int deleteByExample(OrderConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderConfig record);

    int insertSelective(OrderConfig record);

    List<OrderConfig> selectByExample(OrderConfigExample example);

    OrderConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderConfig record, @Param("example") OrderConfigExample example);

    int updateByExample(@Param("record") OrderConfig record, @Param("example") OrderConfigExample example);

    int updateByPrimaryKeySelective(OrderConfig record);

    int updateByPrimaryKey(OrderConfig record);
}
