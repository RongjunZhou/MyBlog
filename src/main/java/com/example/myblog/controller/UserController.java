package com.example.myblog.controller;

import com.example.myblog.Utils.JWTUtils;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Comment;
import com.example.myblog.exception.OperationFailException;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("requestBlog")
    public Blog requestBlogByName(@NotBlank String blogName){
        if(userService.requestBlogByName(blogName)==null){
            throw new OperationFailException(404,"请求资源不存在");
        }
        return userService.requestBlogByName(blogName);
    }

    @PostMapping("/dateLine")
    public List<Blog> requestBlogByData(@NotBlank Integer count){
        List<Blog> blogs=userService.requestBlogByDate(count);
        if(blogs==null){
            throw new OperationFailException(403,"没有更多记录了");
        }
        return blogs;
    }


    @PostMapping("/addComment")
    public boolean addComment(@Validated Comment comment, HttpServletRequest request){
        comment.setCommenter(JWTUtils.verifyToken(request.getHeader("token")).getClaim("username").asString());
        System.out.println(JWTUtils.verifyToken(request.getHeader("token")).getClaim("username").asString());
        return userService.addComment(comment);
    }

    @PostMapping("/getComment")
    public List<Comment> requestComment(@NotBlank Integer blogId){
        return userService.requestComment(blogId);
    }

}
