package com.example.myblog.Config;

import com.example.myblog.interceptors.JWTInterceptorForAdmin;
import com.example.myblog.interceptors.JWTInterceptorForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JWTInterceptorForAdmin jwtInterceptorForAdmin;

    @Autowired
    private JWTInterceptorForUser jwtInterceptorForUser;
    //装配拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptorForAdmin)
                .addPathPatterns("/admin/checkUser")
                .addPathPatterns("/admin/deleteUser")
                .addPathPatterns("/admin/checkBlog")
                .addPathPatterns("/admin/deleteBlog")
                .addPathPatterns("/admin/addBlog");
        registry.addInterceptor(jwtInterceptorForUser)
                .addPathPatterns("/user/addComment")
                .addPathPatterns("/user/fixPassword");
    }
}
