package com.springmall.mapper;

import com.springmall.bean.MallConfig;
import com.springmall.bean.MallConfigExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MallConfigMapper {
    long countByExample(MallConfigExample example);

    int deleteByExample(MallConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallConfig record);

    int insertSelective(MallConfig record);

    List<MallConfig> selectByExample(MallConfigExample example);

    MallConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallConfig record, @Param("example") MallConfigExample example);

    int updateByExample(@Param("record") MallConfig record, @Param("example") MallConfigExample example);

    int updateByPrimaryKeySelective(MallConfig record);

    int updateByPrimaryKey(MallConfig record);
}
