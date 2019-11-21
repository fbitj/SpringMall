package com.springmall.mapper;

import com.springmall.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StorageMapper {
    long countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExample(StorageExample example);

    Storage selectByPrimaryKey(Integer id);

    int selectLastInsertId();

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExampleDetail(@Param("record") Storage2 record, @Param("example") StorageExample example);

    int updateByExampleDeleteId(@Param("record") Storage2 record, @Param("example") StorageExample example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    int updateDeletedByPrimaryKey(@Param("deleted")int value,@Param("id")int id);


}