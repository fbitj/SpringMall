package com.springmall.mapper;

import com.springmall.bean.Admin;
import com.springmall.bean.Admin2;
import com.springmall.bean.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int selectLastInsertId();

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExampleDetail(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int updateDeletedById(@Param("deleted") int value,@Param("id") int id);

    Admin selectAdminByUsername(String username);

    List<String> selectAllPermission();

    List<String> selectPermissionByRoldId(Integer roleId);
}