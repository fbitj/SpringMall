package com.springmall.controller.wxcontroller;

import com.springmall.bean.BaseRespVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/auth")
public class WxAuthorController {
    /**
     * {"errno":0,"data":{"userInfo":{"nickName":"wx","avatarUrl":""},"tokenExpire":"2019-11-21T08:47:25.726","token":"jmlxxsxp176e40wi0x2o9u0lmwo2rnlu"},"errmsg":"成功"}p
     * @param map
     * @return
     */
    @RequestMapping("login")
    public BaseRespVo login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        return BaseRespVo.ok();
    }
}
