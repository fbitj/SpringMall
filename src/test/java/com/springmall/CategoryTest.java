package com.springmall;


import com.springmall.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryTest {
    @Autowired
    CategoryService categoryService;
    @Test
    public void mytest() {
      //  List<CategoryResp> categories = categoryService.queryCategory();
      //  System.out.println(categories);
    }
}
