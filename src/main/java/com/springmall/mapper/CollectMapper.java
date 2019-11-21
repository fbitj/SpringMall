package com.springmall.mapper;

import com.springmall.bean.Collect;
import com.springmall.bean.CollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Pointcut;

public interface CollectMapper {
    long countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    List<Collect> selectByDetail(@Param("user_id") int id, @Param("types") int type,@Param("deleted") boolean deleted);

    List<Collect> selectByDetailId(@Param("user_id") int id);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}