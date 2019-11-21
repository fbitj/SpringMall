package com.springmall.mapper;

import com.springmall.bean.PageRequest;
import com.springmall.bean.Ad;
import com.springmall.bean.AdExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdMapper {
    long countByExample(AdExample example);

    int deleteByExample(AdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    List<Ad> selectByExample(AdExample example);

    Ad selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByExample(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

    List<Ad> selectAllWithParm(PageRequest adRequest);

    int delete(@Param("id") Integer id, @Param("time") Date time);

//    查询未被删除、允许显示、指定数目的广告
    List<Ad> selectAvailAdvertise(int amountLimit);
}