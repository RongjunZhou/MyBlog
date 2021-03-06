package com.example.myblog.controller;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Picture;
import com.example.myblog.exception.OperationFailException;
import com.example.myblog.entity.Result;
import com.example.myblog.service.AdminService;
import com.example.myblog.service.OssService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private OssService ossService;

    @PostMapping("/checkUser")
    public Result checkUserByName(@NotNull String username){
        Account userInstance=adminService.checkUserByName(username);
        if(userInstance==null){
            throw new OperationFailException(402,"用户名为空或用户不存在");
        }
        return new Result(userInstance);
    }

    @PostMapping("/deleteUser")
    public Result deleteUserByName(@NotBlank String username){
         Account accountInstance= adminService.checkUserByName(username);
         if(accountInstance==null){
             throw new OperationFailException(402,"同步数据为空或用户不存在");
         }
         if(accountInstance.getRole() == 1){
            throw new OperationFailException(403,"权限不足");
         }
         return new Result(adminService.deleteUserByName(username));
    }

    @PostMapping("/checkBlog")
    public Result checkBlogByName(@NotBlank String blogName){
        Blog blogInstance=adminService.checkBlogByName(blogName);
        if(blogInstance==null){
            throw new OperationFailException(402,"博客不存在");
        }
        return new Result(blogInstance);
    }

    @PostMapping("/deleteBlog")
    public Result deleteBlogByName(@NotBlank String blogName){
        Blog blogInstance=adminService.checkBlogByName(blogName);
        if(blogInstance==null){
            throw new OperationFailException(402,"博客不存在");
        }
        return new Result(adminService.deleteBlogByName(blogName));
    }

    @PostMapping("/addBlog")
    public Result addBlog(@NotBlank MultipartFile file, @NotBlank String blogName){
        String url=ossService.uploadBlog(file);
        if(!url.isEmpty()){
            Blog blog=new Blog(null,blogName,url);
            if(adminService.addBlog(blog))
                return new Result(true);
            else
                throw new OperationFailException(405,"网络错误");
        }
        else{
            throw new OperationFailException(503,"上传异常");
        }
    }

    @PostMapping("/addPicture")
    public Result addPicture(@NotBlank MultipartFile file, @NotBlank String pictureName){
        String url=ossService.uploadBlog(file);
        if(!url.isEmpty()){
            Picture picture=new Picture(pictureName,url);
            if(adminService.addPicture(picture))
                return new Result(true);
            else
                throw new OperationFailException(405,"网络错误");
        }
        else{
            throw new OperationFailException(503,"上传异常");
        }
    }



}
