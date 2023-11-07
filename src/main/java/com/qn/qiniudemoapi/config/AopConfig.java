package com.qn.qiniudemoapi.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashSet;

@Component
@Slf4j
@Aspect
public class AopConfig {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.qn.qiniudemoapi.annotation.NeedLog)")
    private void useMethod(){

    }

    /**
     * 环绕
     * @return
     */
    @Around(value = "useMethod()")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes!=null;
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        //nginx代理request.getHeader("X-Real-IP)
        String remoteAddr = request.getRemoteAddr();
        log.info("请求ip... " + remoteAddr);
        log.info("请求路径... " + requestURI);
        log.info("请求方式... " + method);

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method methodJava = signature.getMethod();
        log.info("请求方法... " + methodJava.toString());
        Object[] args = proceedingJoinPoint.getArgs();
        HashSet<String> set = new HashSet<>();
        for (Object o : args) {
            set.add(String.valueOf(o));
        }
        log.info("请求参数... " + set);
        Object object;
        try {
            object=proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            //日志打印详细异常信息
            log.info("服务异常 {}",e.getMessage(),e);
            //统一controller处理
            return "error";
        }
        log.info("返回参数... " + object);
        log.info("=====结束=====");
        return object;
    }
}
