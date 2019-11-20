package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/19 23:03
 */
@RestController
@RequestMapping("wx")
public class WxUserController {


    //个人页面用户信息
    /**
     * request
     * /wx/user/index
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"order": {
     * 			"unrecv": 0,
     * 			"uncomment": 0,
     * 			"unpaid": 31,
     * 			"unship": 0
     *                }* 	},
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("user/index")
    public BaseReqVo userIndex(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setData("");
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //商品收藏，取消收藏
    /**
     * request      //wx/collect/addordelete
     * {"type":0,"valueId":1181182}
     * response
     * 删除
     * {"errno":0,"data":{"type":"delete"},"errmsg":"成功"}
     * 添加
     * {"errno":0,"data":{"type":"add"},"errmsg":"成功"}
     * @return
     */
    @RequestMapping("collect/addrodelete")
    public BaseReqVo collectAddrodelete(Map map){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String username = user.getUsername();
        Integer id = user.getId();
        //获取sessionId
//        Serializable id1 = subject.getSession().getId();
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int type = (int) map.get("type");
        int valueId = (int) map.get("valueId");
        baseReqVo.setData("hello");
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }




}
