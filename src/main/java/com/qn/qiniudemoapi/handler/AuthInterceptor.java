package com.qn.qiniudemoapi.handler;

import com.qn.qiniudemoapi.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 请求头拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            return true;//通过所有OPTION请求
        }
        String authorization = request.getHeader("Authorization");
        String decrypt = JwtUtil.decrypt(authorization);
        return decrypt != null;
    }
}
