package com.springmall.mapper;

import com.springmall.bean.Address;
import com.springmall.bean.AddressExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    Integer queryUserId(Integer id);

    Date queryAddTime(Integer id);

    int deleteAddress(Integer id);

    int updateDefault();

    int selectById(Integer id);

    String queryRegionName(Integer id);
}