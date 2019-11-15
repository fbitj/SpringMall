package com.springmall.controller;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Storage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @RequestMapping("create")
    public BaseReqVo<Storage> create() {
        return new BaseReqVo<>();
    }
}
