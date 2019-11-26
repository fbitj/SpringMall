package com.springmall.aop;

import java.util.Date;

import com.springmall.annotation.ActionType;
import com.springmall.annotation.ControllerLog;
import com.springmall.bean.Admin;
import com.springmall.bean.BaseRespVo;
import com.springmall.bean.Log;
import com.springmall.mapper.LogMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspectJ {
    @Autowired
    HttpServletRequest request;
    @Autowired
    LogMapper logMapper;

    /**
     * 用户登陆
     */
    @Pointcut("@annotation(com.springmall.annotation.ControllerLog)")
    public void adminLogin() {
    }

    @AfterReturning(pointcut = "adminLogin()", returning = "respData")
    public void recordLoginLog(JoinPoint joinPoint, Object respData) {
        Class<?> controller = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
        Method method = null;
        try {
            method = controller.getMethod(name,Admin.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }
        Object principal = SecurityUtils.getSubject().getPrincipal();
        Admin admin = (Admin) principal;
        ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);
        String description = controllerLog.description();
        ActionType actionType = controllerLog.actionType();
        String ip = request.getRemoteAddr();
        BaseRespVo baseRespVo = (BaseRespVo) respData;
        int errno = baseRespVo.getErrno();
        Log log = new Log();
        log.setId(0);
        log.setAdmin(admin.getUsername());
        log.setIp(ip);
        log.setType(actionType.getType());
        log.setAction(description);
        if (errno == 0) {
            log.setStatus(true);
            log.setResult("成功");
        } else {
            log.setStatus(false);
            log.setResult("失败");
        }
        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setDeleted(false);
        // 将日志记录到数据库
        logMapper.insertSelective(log);
    }
}
