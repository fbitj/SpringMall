package com.springmall.server;

import com.springmall.bean.Admin;
import com.springmall.bean.Admin2;
import com.springmall.bean.Role;
import com.springmall.bean.Storage;
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

    Storage storageCreate(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException;

    List roleOption();

    Map adminList(String page, int limit, String username, String sort, String order);

    Admin adminCreate(Admin admin, HttpServletRequest request);

    Admin adminUpdate(Admin admin);

    int adminDelete(Admin2 admin2);

    Map roleList(int page,int limit,String name, String sort, String order);

    int roleUpdate(Role role);

    Role roleCreate(Role role);

    Map rolePermissions(int roleId);
}
