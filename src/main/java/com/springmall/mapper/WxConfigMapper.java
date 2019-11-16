package com.springmall.mapper;

import com.springmall.bean.WxConfig;
import com.springmall.bean.WxConfigExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WxConfigMapper {
    long countByExample(WxConfigExample example);

    int deleteByExample(WxConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxConfig record);

    int insertSelective(WxConfig record);

    List<WxConfig> selectByExample(WxConfigExample example);

    WxConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxConfig record, @Param("example") WxConfigExample example);

    int updateByExample(@Param("record") WxConfig record, @Param("example") WxConfigExample example);

    int updateByPrimaryKeySelective(WxConfig record);

    int updateByPrimaryKey(WxConfig record);
}
