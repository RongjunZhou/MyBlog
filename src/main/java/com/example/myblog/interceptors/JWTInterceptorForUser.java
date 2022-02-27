package com.example.myblog.interceptors;

import com.example.myblog.Utils.JWTUtils;
import com.example.myblog.exception.OperationFailException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
//用户鉴权拦截
public class JWTInterceptorForUser implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        if(token.isEmpty()){
            throw new OperationFailException(500,"请登录");
        }
        JWTUtils.verifyToken(token);
        return true;
    }
}
