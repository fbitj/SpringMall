package com.springmall.aop;
import java.io.File;
import java.util.Date;


import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Log;
import com.springmall.bean.Log2;
import com.springmall.mapper.LogMapper;
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
    private final static   Logger logger = Logger.getLogger(Myaspect.class);

    @Autowired
    LogMapper logMapper;

    @Pointcut("execution(* com.springmall.controller..*(..))")
    public void mypoint() {
    }


    @Before("mypoint()")
    public void logMassage(JoinPoint pjp) {

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
            String requestURI = request.getRequestURI();
            System.out.println(requestURI);
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
        if(methodName != null && response != null){
            status = response.getStatus();
            if (methodName.contains("queryAddressList")) {
                if (status != 200) {
                    action = "查询地址列表";
                    status = 0;
                    result = "操作失败";
                } else {
                    action = "查询地址列表";
                    status = 1;
                }
            }
        }
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

        if (response != null) {
            //获取状态码
            status = response.getStatus();
            System.out.println(status);
            if (status != 200) {
                status = 0;
                result = "操作失败";
                //操作结果
            } else {
                status = 1;
            }
        }
        if(methodName != null){
            if (methodName.contains("/order")) {
                action = "";
            }
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
        logger.fatal(pjp);


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

/*   // @Around("mypoint()")
    public void around(ProceedingJoinPoint pjp){
        int type = 0;
        String action = "";
        String result = "";
        String username = "";
        int status = 0;
        String IP = "";
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
        if(methodName != null && response != null){
            status = response.getStatus();
            if (methodName.contains("queryAddressList")) {
                if (status != 200) {
                    action = "查询地址列表";
                    status = 0;
                    result = "操作失败";
                } else {
                    action = "查询地址列表";
                    status = 1;
                }
            }
        }
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

        if(request != null) {
            int i = logMapper.insertDetail(log);
            if(i == 1){
                return;
            }
        }

        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        pjp.getStaticPart()
    }*/
}

