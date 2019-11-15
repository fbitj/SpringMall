package com.springmall.mapper;

import com.springmall.bean.storage;
import com.springmall.bean.storageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface storageMapper {
    long countByExample(storageExample example);

    int deleteByExample(storageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(storage record);

    int insertSelective(storage record);

    List<storage> selectByExample(storageExample example);

    storage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") storage record, @Param("example") storageExample example);

    int updateByExample(@Param("record") storage record, @Param("example") storageExample example);

    int updateByPrimaryKeySelective(storage record);

    int updateByPrimaryKey(storage record);
}