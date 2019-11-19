package com.springmall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Comment;
import com.springmall.bean.Goods;
import com.springmall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 分页查询商品评论
     * page=1&limit=20&sort=add_time&order=desc
     * @param page
     * @param limit
     * @param add_time
     * @param order
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo queryComments(int page, int limit, String add_time, String order){
        BaseReqVo baseReqVo = new BaseReqVo();
        List<Comment> comments = commentService.queryCommentsByPage(page, limit, add_time, order);
        PageInfo<Comment> goodsInfo = new PageInfo<>(comments);
        long total = goodsInfo.getTotal();
        //2、结果封装并返回
        HashMap<String, Object> goodsData = new HashMap<>();
        goodsData.put("total", total);
        goodsData.put("items", comments);
        baseReqVo.setData(goodsData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 删除评论
     * @param comment
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteComment(@RequestBody Comment comment){
        int res = commentService.deleteCommentById(comment.getId());
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
