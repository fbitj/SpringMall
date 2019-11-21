package com.springmall.mapper;

import com.springmall.bean.Role;
import com.springmall.bean.Role2;
import com.springmall.bean.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int selectLastInsertId();

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExampleDetail(@Param("record") Role2 record, @Param("example") RoleExample example);

    int updateByExampleDetailSet(@Param("record") Role2 record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int updateDeletedById(@Param("deleted") int value,@Param("id") int id);

    int updateEnabledById(@Param("enabled") int value,@Param("id") int id);

    int updateEnabledByIdSet(@Param("enabled") int value,@Param("id") String id);

    int deleteRoleById(@Param("deleted") int value,@Param("id") int id);

    String selectById(Integer id);
}