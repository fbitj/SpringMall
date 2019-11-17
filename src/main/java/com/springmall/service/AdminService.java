package com.springmall.service;

import com.springmall.bean.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/16 15:34
 */
public interface AdminService {

    int login(Admin admin);

    Storage storageCreate(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException;

    List roleOption();

    Map adminList(String page, int limit, String username, String sort, String order);

    Map adminCreate(Admin admin, HttpServletRequest request);

    int sameToAdminName(Admin admin);

    Admin adminUpdate(Admin admin);

    int adminDelete(Admin2 admin2);

    Map roleList(int page,int limit,String name, String sort, String order);

    int roleUpdate(Role role);

    Role roleCreate(Role role);

    Map rolePermissions(int roleId);

    int sameToRoleName(Role role);

    int roleDelete(Role role);

    Map logList(int page, int limit ,String name, String sort, String order);

    Map storageList(int page, int limit ,String key, String name, String sort, String order);

    Storage storageUpdate(Storage storage);

    int storageDelete(Storage storage);
}
