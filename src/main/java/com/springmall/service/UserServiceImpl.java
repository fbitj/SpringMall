package com.springmall.service;
import java.util.*;

import com.springmall.bean.User;
import com.springmall.bean.UserExample;
import com.springmall.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.*;
import com.springmall.utils.SubjectUtil;
import java.util.HashMap;
import java.util.List;

/**
 * 用户管理——会员管理——service
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    TopicMapper topicMapper;

    @Override
    public List<User> queryUserList(String username, String mobile) {
        UserExample userExample = new UserExample();
        List<User> users = null;
        if ((username == null||"".equals(username) )&&(mobile == null||"".equals(mobile))){
            users = userMapper.selectByExample(userExample);
        } else if ((username != null|| username.length()>0)&& (mobile == null || "".equals(mobile))) {
            userExample.createCriteria().andUsernameLike("%"+username+"%");
            users = userMapper.selectByExample(userExample);
        } else if ((mobile != null || mobile.length()>0) && (username == null|| "".equals(username))){
            userExample.createCriteria().andMobileEqualTo(mobile);
            users = userMapper.selectByExample(userExample);
        } else {
            userExample.createCriteria().andUsernameLike("%"+username+"%").andMobileEqualTo(mobile);
            users = userMapper.selectByExample(userExample);
        }
        return users;
    }

    @Override
    public int userLogin(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0)
            return 2;
        return 1;
    }


    @Override
    public Map collectAddOrDelete(int type, int value, int id) {
        HashMap<Object, Object> map = new HashMap<>();
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andUserIdEqualTo(id).andTypeEqualTo((byte)type).andValueIdEqualTo(value).andDeletedEqualTo(false);
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        if(collects.size() != 0){   //说明已经有收藏了， 则删除收藏
            Collect collect = collects.get(0);
            collectMapper.deleteByPrimaryKey(collect.getId());
            map.put("type","delete");
            return map;
        }
        Collect collect = new Collect();
        collect.setId(null);
        collect.setUserId(id);
        collect.setValueId(value);
        collect.setType((byte)type);
        Date date = new Date();
        collect.setAddTime(date);
        collect.setUpdateTime(date);
        collect.setDeleted(false);
        collectMapper.insert(collect);
        map.put("type","add");
        return map;
    }

    @Override
    public Map collectList(int type, int page, int size, int id) {
        HashMap<Object, Object> map = new HashMap<>();
        //分页查询
        PageHelper.startPage(page, size);
        List<Collect> collects = collectMapper.selectByDetail(id, type, false);
        PageInfo<Collect> objectPageInfo = new PageInfo<Collect>(collects);
        long total = objectPageInfo.getTotal();
        map.put("totalPages",(int)total);

        ArrayList<Object> arrayList = new ArrayList<>();
        if(collects.size() != 0) {
            for (Collect collect : collects) {
                GoodsExample goodsExample = new GoodsExample();
                goodsExample.createCriteria().andGoodsSnEqualTo(String.valueOf(collect.getValueId()).trim()).andDeletedEqualTo(false);
                List<Goods> goods = goodsMapper.selectByExample(goodsExample);
                HashMap<Object, Object> map1 = new HashMap<>();
                Goods good = goods.get(0);
                map1.put("brief", good.getBrief());
                map1.put("picUrl", good.getPicUrl());
                map1.put("valueId", collect.getValueId());
                map1.put("name", good.getName());
                map1.put("id", good.getId());
                map1.put("type", collect.getType());
                map1.put("retailPrice", good.getRetailPrice());
                arrayList.add(map1);
            }
        }
        map.put("collectList",arrayList);
        return map;
    }

    @Override
    public Map userIndex(int id) {
        HashMap<Object, Object> map = new HashMap<>();
        HashMap<Object, Object> map1 = new HashMap<>();
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUserIdEqualTo(id).andDeletedEqualTo(false);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        int recvCount = 0;
        int commentCount = 0;
        int paidCount = 0;
        int shipCount = 0;
        for (Order order : orders) {
            Short orderStatus = order.getOrderStatus();
            if(orderStatus == 101){
                paidCount ++;
            }
            if(orderStatus == 201){
                shipCount ++;
            }
            if(orderStatus == 301){
                recvCount ++;
            }
            if(orderStatus == 401){
                commentCount ++;
            }
        }
        map1.put("nurecv",recvCount);
        map1.put("uncomment",commentCount);
        map1.put("unpaid",paidCount);
        map1.put("unship",shipCount);
        map.put("order",map1);
        return map;
    }

    @Override
    public Map commentList(int valueId, int type, int size, int page, int showType) {
        HashMap<Object, Object> map = new HashMap<>();
        ArrayList<Object> list = new ArrayList<>();
        CommentExample commentExample = new CommentExample();
        if(type == 0) { //商品评论
            if (showType == 1) {
                commentExample.createCriteria().andTypeEqualTo((byte) type)
                        .andValueIdEqualTo(valueId).andHasPictureEqualTo(true).andDeletedEqualTo(false);
            } else {
                commentExample.createCriteria().andTypeEqualTo((byte) type).andValueIdEqualTo(valueId).andDeletedEqualTo(false);
            }
        }
        if(type == 1){ //专题评论
            commentExample.createCriteria().andTypeEqualTo((byte) type).andValueIdEqualTo(valueId);
        }

        String s = "update_time" + " " + "desc";
        PageHelper.startPage(page,size,s);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        if(comments.size() != 0) {
            for (Comment comment : comments) {
                HashMap<Object, Object> map2 = new HashMap<>();
                HashMap<Object, Object> map1 = new HashMap<>();
                User user = userMapper.selectByPrimaryKey(comment.getUserId());
                map1.put("nickName", user.getNickname());
                map1.put("avatarUrl", user.getAvatar());
                map2.put("userInfo", map1);
                map2.put("addTime", comment.getAddTime());
                map2.put("picList", comment.getPicUrls());
                map2.put("content", comment.getContent());
                list.add(map2);
            }
        }
        int size1 = pageInfo.getSize();
        map.put("data",list);
        map.put("count",size1);
        map.put("currentPage",page);
        return map;
    }

    @Override
    public Map commentCount(int valueId, int type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo((byte)type);
        long allCount = commentMapper.countByExample(commentExample);
        commentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo((byte)type).andHasPictureEqualTo(true);
        long hasPicCount = commentMapper.countByExample(commentExample);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("hasPicCount",(int)hasPicCount);
        map.put("allCount",(int)allCount);
        return map;
    }

    @Override
    public Map topicList(int pag, int size) {
        HashMap<Object, Object> map = new HashMap<>();
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andDeletedEqualTo(false);
        //分页    按价格升序，便宜的在前面
        String s = "price" + " " + "asc";
        PageHelper.startPage(pag,size,s);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        topicPageInfo.getSize();

        ArrayList<Topic> list = new ArrayList<>();
        if(topics.size() != 0) {
            for (Topic topic : topics) {
                list.add(topic);
            }
        }
        map.put("data",list);
        map.put("count",topics.size());
        return map;
    }

    @Override
    public List topicRelated(int id) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andDeletedEqualTo(false);
        List<Topic> topics1 = topicMapper.selectByExample(topicExample);
        return topics1;
    }

    @Override
    public Map topicDetail(int id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("topic",topic);
        map.put("goods",new ArrayList<>());
        return map;
    }

    @Override
    public Comment commentPost(Comment comment) {
        Integer id1 = SubjectUtil.getUser().getId();
        comment.setUserId(id1);
        Date date = new Date();
        comment.setAddTime(date);
        comment.setUpdateTime(date);
        comment.setDeleted(false);
        comment.setPicUrls(comment.getPicUrls());
        commentMapper.insertDetail(comment);
        int id = commentMapper.selectLastInsertId();
        comment.setId(id);
        return comment;
    }

    @Override
    public int register(HashMap<String, String> userInfoMap) {
        return userMapper.insertUser(userInfoMap);
    }

    @Override
    public int isUserExist(String username) {
        return userMapper.selectUserCountByName(username);
    }

    @Override
    public int isMobileExist(String mobile) {
        return userMapper.selectUserCountByMobile(mobile);
    }

    // 根据手机号重置用户密码
    @Override
    public int resetPassword(String password, String mobile) {
        User user = new User();
        user.setPassword(password);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andMobileEqualTo(mobile);
        return userMapper.updateByExampleSelective(user, userExample);

    }

    // 记录当前用户的登出时间和登陆的ip地址信息
    @Override
    public void recordUserLoginInfo(String ip) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal(); // 获取当前用户对象
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(principal.getUsername());
        User user = new User();
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(ip);
        int res = userMapper.updateByExampleSelective(user, userExample);
    }


}
