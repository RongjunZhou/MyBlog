package com.example.myblog.service;


import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Picture;

public interface AdminService {
    Account checkUserByName(String username);
    boolean deleteUserByName(String username);
    Blog checkBlogByName(String blogName);
    boolean deleteBlogByName(String blogName);
    boolean addBlog(Blog blog);
    boolean addPicture(Picture picture);
}
