package com.springmall.mapper;

import com.springmall.bean.*;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int deleteByRoleId(Integer id);

    int insert(Permission record);

    int insert4(Permission4 record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectByRoleId(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission1> selectAllP1();

    List<Permission2> selectAllP2();

    List<Permission3> selectAllP3();

    ArrayList<String> selectPermsByRolesId(ArrayList<Integer> rolesIdList);
}