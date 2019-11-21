package com.springmall.controller.weixin;


import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Comment;
import com.springmall.bean.User;

import com.springmall.service.UserService;
import com.springmall.utils.SubjectUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/19 23:03
 */
@RestController
@RequestMapping("wx")
public class WxUserController {

    @Autowired
    UserService userService;



    //个人页面用户信息
    /**
     * request
     * /wx/user/index
     * response
     * {
     * "errno": 0,
     *  "data": {
     *     "order": {
     *     "unrecv": 0,         代收货
     *     "uncomment": 0,      待评价
     *     "unpaid": 31,        待支付
     *     "unship": 0          待发货
     *     }*
     * },
     * "errmsg": "成功"
     * }
     *
     * @return
     */
    @RequestMapping("user/index")
    public BaseReqVo userIndex() {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        User user = SubjectUtil.getUser();
        Integer id = user.getId();
        Map map = userService.userIndex(id);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return BaseReqVo.ok(map);
//        return baseReqVo;
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
     *
     * @return
     */
    @RequestMapping("collect/addordelete")
    public BaseReqVo collectAddrodelete(@RequestBody Map map) {
        User user = SubjectUtil.getUser();
        Integer id = user.getId();
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int type = (int) map.get("type");
        int valueId = (int) map.get("valueId");
        Map map1 = userService.collectAddOrDelete(type, valueId, id);
        baseReqVo.setData(map1);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //收藏列表
    /**
     * request
     * wx/collect/list?type=0&page=1&size=10
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"totalPages": 1,
     * 		"collectList": [{
     * 			"brief": "好系统!",
     * 			"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/9ouab1wa0a7ll196hfe9.jpg",
     * 			"valueId": 1181047,
     * 			"name": "杨豆豆",
     * 			"id": 261,
     * 			"type": 0,
     * 			"retailPrice": 123.00
     *           }]*
     *      },
     * 	"errmsg": "成功"
     * }
     * @param type 收藏类型
     * @param page 页数
     * @param size 分页大小
     * @return
     */
    @RequestMapping("collect/list")
    public BaseReqVo collectList(int type,int page,int size){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        User user = SubjectUtil.getUser();
        int id = user.getId();
        Map map = userService.collectList(type, page, size, id);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }



    /**
     * request
     * 商品评论
     * //wx/comment/list?valueId=1114011 type=0  size=20  page=1  showType=1
     * 专题评论
     * wx/comment/list?valueId=358  type=1  showType=0  page=1  size=5
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"data": [{
     * 			"userInfo": {
     * 				"nickName": "dr lan",
     * 				"avatarUrl": ""
     *           },
     * 			"addTime": "2019-11-20 02:08:50",
     * 			"picList": ["http://192.168.2.100:8081/wx/storage/fetch/qt1kc5766scz6fg5ja7q.png"],
     * 			"content": "laji"
     * 		 }],
     * 		"count": 1,
     * 		"currentPage": 1
     *    },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("comment/list")     //                               有图则为1，全部则为0
    public BaseReqVo commentList(int valueId, int type, int size, int page, int showType){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = userService.commentList(valueId, type, size, page, showType);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //评论总数
    /**
     * request
     * //wx/comment/count?valueId=1114011&type=0
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"hasPicCount": 1,
     * 		"allCount": 2
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("comment/count")
    public BaseReqVo commentCount(int valueId ,int type){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = userService.commentCount(valueId, type);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    //发布评论
    /**
     * request
     * {
     * 	"type": 1,
     * 	"valueId": "358",
     * 	"content": "本仙女可是腾云驾雾！！",
     * 	"star": 3,
     * 	"hasPicture": true,
     * 	"picUrls": ["http://192.168.2.100:8081/wx/storage/fetch/8uvuwsv946kme1hgolcu.jpg"]
     * }
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1102,
     * 		"valueId": 358,
     * 		"type": 1,
     * 		"content": "本仙女可是腾云驾雾！！",
     * 		"userId": 1,
     * 		"hasPicture": true,
     * 		"picUrls": ["http://192.168.2.100:8081/wx/storage/fetch/8uvuwsv946kme1hgolcu.jpg"],
     * 		"star": 3,
     * 		"addTime": "2019-11-20 07:42:39",
     * 		"updateTime": "2019-11-20 07:42:39"
     *        },
     * 	"errmsg": "成功"
     * }
     * @param
     * @param
     * @return
     */
    @RequestMapping("comment/post")
    public BaseReqVo commentPost(@RequestBody Comment comment){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Comment comment1 = userService.commentPost(comment);
        baseReqVo.setData(comment1);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //专题详情
    /**
     * request
     * wx/topic/detail?id=268
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"topic": {
     * 			"id": 268,
     * 			"title": "米饭好吃的秘诀：会呼吸的锅",
     * 			"subtitle": "今年1月份，我们联系到了日本伊贺地区的长谷园，那里有着180年伊贺烧历史的窑厂。...",
     * 			"price": 0.00,
     * 			"readCount": "33.3k",
     * 			"picUrl": "https://yanxuan.nosdn.127.net/14920623353130483.jpg",
     * 			"sortOrder": 0,
     * 			"goods": [],
     * 			"addTime": "2018-01-31 19:00:00",
     * 			"updateTime": "2018-01-31 19:00:00",
     * 			"deleted": false,
     * 			"content": "<img src=\"//yanxuan.nosdn.127.net/75c55a13fde5eb2bc2dd6813b4c565cc.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/e27e1de2b271a28a21c10213b9df7e95.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/9d413d1d28f753cb19096b533d53418d.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/64b0f2f350969e9818a3b6c43c217325.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/a668e6ae7f1fa45565c1eac221787570.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/0d4004e19728f2707f08f4be79bbc774.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/79ee021bbe97de7ecda691de6787241f.jpg\">"
     *                },
     * 		"goods": []*
     * 		},
     * 	"errmsg": "成功"
     * }
     * @param id
     * @return
     */
    @RequestMapping("topic/detail")
    public BaseReqVo topicDetail(int id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = userService.topicDetail(id);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    //专题推荐
    /**
     * request
     * //wx/topic/related?id=358
     * response
     *  {
     * 	"errno": 0,
     * 	"data": [{
     * 		"id": 264,
     * 		"title": "设计师们推荐的应季好物",
     * 		"subtitle": "原创",
     * 		"price": 29.90,
     * 		"readCount": "77.7k",
     * 		"picUrl": "https://yanxuan.nosdn.127.net/14918201901050274.jpg",
     * 		"sortOrder": 0,
     * 		"goods": [],
     * 		"addTime": "2018-01-31 19:00:00",
     * 		"updateTime": "2019-08-23 04:23:12",
     * 		"deleted": false,
     * 		"content": "<img src=\"//yanxuan.nosdn.127.net/75c55a13fde5eb2bc2dd6813b4c565cc.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/e27e1de2b271a28a21c10213b9df7e95.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/9d413d1d28f753cb19096b533d53418d.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/64b0f2f350969e9818a3b6c43c217325.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/a668e6ae7f1fa45565c1eac221787570.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/0d4004e19728f2707f08f4be79bbc774.jpg\">\n    <img src=\"//yanxuan.nosdn.127.net/79ee021bbe97de7ecda691de6787241f.jpg\">"
     *        }],
     * 	"errmsg": "成功"
     * }
     * @param id
     * @return
     */
    @RequestMapping("topic/related")
    public BaseReqVo topicRelated(int id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        List list = userService.topicRelated(id);
        baseReqVo.setData(list);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    /**
     * request
     * ///wx/topic/list?page=1&size=10
     * response
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"data": [{
     * 			"id": 358,
     * 			"title": "车",
     * 			"subtitle": "汽车",
     * 			"price": 10.00,
     * 			"readCount": "10k",
     * 			"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/kwig1o5f6sf6e5md3b8r.jpg"
     *          }],
     * 		"count": 41*
     * 	    },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("topic/list")
    public BaseReqVo topicList(int page, int size){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map map = userService.topicList(page, size);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

}


