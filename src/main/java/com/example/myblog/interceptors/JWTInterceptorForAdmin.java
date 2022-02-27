package com.example.myblog.interceptors;

import com.example.myblog.Utils.JWTUtils;

import com.example.myblog.exception.OperationFailException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//管理员鉴权拦截
@Component
public class JWTInterceptorForAdmin implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        String role= JWTUtils.verifyToken(token).getClaim("role").asString();
        if(role.equals("1"))
            return true;
        throw new OperationFailException(403,"权限不足");
    }
}
