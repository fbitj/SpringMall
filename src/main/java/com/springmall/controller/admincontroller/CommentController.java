package com.springmall.controller.admincontroller;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseRespVo;
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
    public BaseRespVo queryComments(int page, int limit, String add_time, String order){
        BaseRespVo BaseRespVo = new BaseRespVo();
        List<Comment> comments = commentService.queryCommentsByPage(page, limit, add_time, order);
        PageInfo<Comment> goodsInfo = new PageInfo<>(comments);
        long total = goodsInfo.getTotal();
        //2、结果封装并返回
        HashMap<String, Object> goodsData = new HashMap<>();
        goodsData.put("total", total);
        goodsData.put("items", comments);
        BaseRespVo.setData(goodsData);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    /**
     * 删除评论
     * @param comment
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo deleteComment(@RequestBody Comment comment){
        int res = commentService.deleteCommentById(comment.getId());
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }
}
