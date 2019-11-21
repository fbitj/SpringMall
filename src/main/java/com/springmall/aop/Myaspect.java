package com.springmall.aop;
import java.io.File;
import java.util.Date;


import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Log;
import com.springmall.bean.Log2;
import com.springmall.bean.Log3;
import com.springmall.mapper.LogMapper;
import com.springmall.utils.LogUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;


/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/18 20:03
 */
@Component
@Aspect
public class Myaspect {
//    private final static   Logger logger = Logger.getLogger(Myaspect.class);

    @Autowired
    LogMapper logMapper;

//    @Pointcut("execution(* com.springmall.controller..*(..))")
    @Pointcut("execution(* com.springmall.controller.admin..*(..))")
    public void mypoint() {
    }


    @AfterReturning(value = "mypoint()",returning = "base")
    public void logMassage(JoinPoint pjp,Object base) {
        BaseReqVo baseReqVo = (BaseReqVo) base;

        int errno = baseReqVo.getErrno();
        System.out.println(errno);
        String errmsg = baseReqVo.getErrmsg();
        System.out.println(errmsg);
        //保存日志文件到磁盘
        File file = new File("C:/SpringMallImage/log");
        if(!file.exists()){
            file.mkdirs();
        }
        int type = 0;
        String action = "";
        String result = "";
        String username = "";
        int status = 0;
        String IP = "";
        String uri = "";
        InetAddress ip = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        //获取request  封装ip地址
        //获取response 获取状态码，请求方法名称
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (servletRequestAttributes != null) {
            response = servletRequestAttributes.getResponse();
            request = servletRequestAttributes.getRequest();
            String internetIp = request.getHeader("x-forwarded-for");
            if (internetIp == null || internetIp.length() == 0 || "unknown".equalsIgnoreCase(internetIp)) {
                internetIp = request.getRemoteAddr();
                if (internetIp.equals("127.0.0.1") || internetIp.equals("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    internetIp = inet.getHostAddress();
                    ip = inet;
                }
            }
        }

        //从session🐟域中取出username，如果没有取出来，则访问失败
        //执行before时，总是为空
        if(request != null){
            uri = request.getRequestURI();
            System.out.println(uri);
            String username1 = (String) request.getSession().getAttribute("username");
            username = username1;
        }

        String string = ip + "";
        int length = string.length();
        if (length > 5) {
            IP = string.substring(string.indexOf("/") + 1, length);
        }
        //获取真实访问ip
        System.out.println(IP);
        // 获取当前方法的对象
        Object target = pjp.getTarget();
        System.out.println(target);
        // 获取当前方法名称
        String methodName = pjp.getSignature().getName();
        System.out.println(methodName);
        // 获取方法的参数
        Object[] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));

        // 获取方法参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();


        if (null != methodName) {
            if (methodName.contains("user/")) {
                type = 0;
            }
            if (methodName.contains("login")) {
                type = 1;
            }
            if (methodName.contains("/order")) {
                type = 2;
            } else{
                type = 3;
            }
        }

        if("/admin/auth/login".equals(uri)){
            action = "登陆后台管理系统";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "登陆失败";
                //return new Log3(action,status,result);
            }
        }else if("/admin/auth/info".equals(uri)){
            action = "查看";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }else if("/admin/dashboard".equals(uri)){
            action = "查看首页信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/collect/list".equals(uri)){
            action = "查询用户收藏";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/feedback/list".equals(uri)){
            action = "查询意见反馈";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/user/list".equals(uri)){
            action = "查询会员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/footprint/list".equals(uri)){
            action = "查询用户足迹";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/history/list".equals(uri)){
            action = "查询搜索历史";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/address/list".equals(uri)){
            action = "查询收货地址";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/groupon/listRecord".equals(uri)){
            action = "查看团购详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/groupon/update".equals(uri)){
            action = "编辑团购信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/groupon/delete".equals(uri)){
            action = "删除团购信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/groupon/create".equals(uri)){
            action = "添加团购信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/groupon/list".equals(uri)){
            action = "查询团购";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/ad/update".equals(uri)){
            action = "编辑广告信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/ad/read".equals(uri)){
            action = "查看广告详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/ad/delete".equals(uri)){
            action = "删除广告";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/ad/create".equals(uri)){
            action = "添加广告";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/ad/list".equals(uri)){
            action = "查询广告";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/topic/update".equals(uri)){
            action = "编辑专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/topic/read".equals(uri)){
            action = "查看专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/topic/delete".equals(uri)){
            action = "删除专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/topic/create".equals(uri)){
            action = "添加专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/topic/list".equals(uri)){
            action = "查询专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/coupon/listuser".equals(uri)){
            action = "查询用户优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/coupon/update".equals(uri)){
            action = "编辑优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/coupon/read".equals(uri)){
            action = "查看优惠券详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/coupon/delete".equals(uri)){
            action = "删除优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/coupon/create".equals(uri)){
            action = "添加优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/coupon/list".equals(uri)){
            action = "查询优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/config/wx".equals(uri)){
            action = "小程序查看或编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/config/express".equals(uri)){
            action = "运费详情，编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/config/mall".equals(uri)){
            action = "商场详情，编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/config/order".equals(uri)){
            action = "订单详情，编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/index/write".equals(uri)){
            action = "权限测试写";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/index/read".equals(uri)){
            action = "权限测试读";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/stat/user".equals(uri)){
            action = "用户统计";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/stat/order".equals(uri)){
            action = "订单统计";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/stat/goods".equals(uri)){
            action = "商品统计";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/admin/update".equals(uri)){
            action = "编辑管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/admin/read".equals(uri)){
            action = "管理员详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/admin/delete".equals(uri)){
            action = "删除管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/admin/create".equals(uri)){
            action = "添加管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/admin/list".equals(uri)){
            action = "查询管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/options".equals(uri)){
            action = "显示管理员权限信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/permissions".equals(uri)){
            action = "角色权限变更";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/update".equals(uri)){
            action = "编辑角色";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/read".equals(uri)){
            action = "角色详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/delete".equals(uri)){
            action = "删除角色";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/permissions".equals(uri)){
            action = "权限信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/create".equals(uri)){
            action = "添加角色";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/role/list".equals(uri)){
            action = "角色查询";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/storage/update".equals(uri)){
            action = "编辑图片";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/storage/read".equals(uri)){
            action = "查看图片详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/storage/delete".equals(uri)){
            action = "删除图片";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/storage/create".equals(uri)){
            action = "上传图片";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/storage/list".equals(uri)){
            action = "查询图片信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/brand/update".equals(uri)){
            action = "品牌编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/brand/read".equals(uri)){
            action = "品牌详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/brand/delete".equals(uri)){
            action = "删除品牌";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/brand/create".equals(uri)){
            action = "添加品牌";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/brand/list".equals(uri)){
            action = "查询品牌信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/order/refund".equals(uri)){
            action = "订单退款";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/order/reply".equals(uri)){
            action = "订单商品回复";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/order/ship".equals(uri)){
            action = "订单发货";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/order/detail".equals(uri)){
            action = "订单详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/order/list".equals(uri)){
            action = "查询订单";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/keyword/update".equals(uri)){
            action = "编辑关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/keyword/read".equals(uri)){
            action = "关键词详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/keyword/delete".equals(uri)){
            action = "删除关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/keyword/create".equals(uri)){
            action = "添加关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/keyword/list".equals(uri)){
            action = "查询关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/category/update".equals(uri)){
            action = "类目编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/category/read".equals(uri)){
            action = "类目详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/category/delete".equals(uri)){
            action = "删除类目";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/category/create".equals(uri)){
            action = "添加类目";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/category/list".equals(uri)){
            action = "查询类目信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/issue/update".equals(uri)){
            action = "通用问题编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/issue/delete".equals(uri)){
            action = "通用问题删除";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/issue/create".equals(uri)){
            action = "通用问题添加";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/issue/list".equals(uri)){
            action = "通用问题查询";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/goods/detail".equals(uri)){
            action = "商品详情信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/goods/update".equals(uri)){
            action = "编辑商品信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/goods/delete".equals(uri)){
            action = "删除商品";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/goods/delete".equals(uri)){
            action = "上架商品";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/goods/list".equals(uri)){
            action = "查询商品信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/comment/delete".equals(uri)){
            action = "删除评论";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                //return new Log3(action,status,result);
            }
        }
        else if("/admin/comment/list".equals(uri)){
            action = "查询评论";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                //return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                //return new Log3(action,status,result);
            }
        }else{
            action = "其它查询";
            status = 1;
            result = "成功";
        }
            /*Log3 logMessage = LogUtil.getLogMessage(baseReqVo, uri);
            action = logMessage.getAction();
            status = logMessage.getStatus();
            result = logMessage.getResult();*/
        //封装数据到javabean，然后转存到数据库

        Log2 log = new Log2();
        log.setId(null);
        log.setAdmin(username);
        log.setIp(IP);
        log.setType(type);
        log.setAction(action);
        log.setStatus(status);
        log.setResult(result);
        log.setComment("");
        Date addTime = new Date();
        log.setAddTime(addTime);
        log.setUpdateTime(addTime);
        log.setDeleted(0);
        System.out.println(log);

        if(!"/admin/log/list".equals(uri)) {
            logMapper.insertDetail(log);
        }
        /*if(request != null) {
            org.apache.log4j.MDC.put("admin", username);
            org.apache.log4j.MDC.put("ip", IP);
            org.apache.log4j.MDC.put("`type`", type);
            org.apache.log4j.MDC.put("action", action);
            org.apache.log4j.MDC.put("status", status);
            org.apache.log4j.MDC.put("result", result);
            org.apache.log4j.MDC.put("comment", "");
            Date addTime1 = new Date();
            org.apache.log4j.MDC.put("add_time", addTime1);
            org.apache.log4j.MDC.put("update_time", addTime1);
            org.apache.log4j.MDC.put("deleted", 0);
        }*/
//        logger.fatal(pjp);

        /*if(request != null) {
            int i = logMapper.insertDetail(log);
            if(i == 1){
                return;
            }
        }*/
    }
/*
 // 创建日志对象用于保存
      BsUserLog4j log = new BsUserLog4j();
      BsUserInfo user = JsonUtils.jsonToPojo(redisService.get(args[0].toString()), BsUserInfo.class) ;

       //用户登出
       if(actionType == "04") {
           //删除缓存中的token值
           redisService.del(args[0].toString());
           System.out.println("已删除缓存中token值");
       }
       // 设置log信息并保存数据库
       log.setLogId(new BigDecimal(IDUtils.genId()));
       log.setUsername(user.getUsername());
       log.setActionType(actionType);
       log.setActionTime(actionTime);
       log.setActionDesc(actionDesc);
       log.setInternetIp(internetIp);

       //添加日志到数据库
       logService.addLog(log);
       System.out.println("添加日志： " + log.toString());

   }*//*
    }*/

}