package com.springmall.mapper;

import com.springmall.bean.ExpressConfig;
import com.springmall.bean.ExpressConfigExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpressConfigMapper {
    long countByExample(ExpressConfigExample example);

    int deleteByExample(ExpressConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExpressConfig record);

    int insertSelective(ExpressConfig record);

    List<ExpressConfig> selectByExample(ExpressConfigExample example);

    ExpressConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExpressConfig record, @Param("example") ExpressConfigExample example);

    int updateByExample(@Param("record") ExpressConfig record, @Param("example") ExpressConfigExample example);

    int updateByPrimaryKeySelective(ExpressConfig record);

    int updateByPrimaryKey(ExpressConfig record);
}
