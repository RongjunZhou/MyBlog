package com.example.myblog.service;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Comment;

import java.util.List;

public interface UserService {
    Account requestUserByName(String username);
    Blog requestBlogByName(String blogName);
    List<Blog> requestBlogByDate(Integer page);
    boolean checkUser(String username);
    boolean addUser(Account account);
    boolean fixPassword(String username,String password);
    boolean addComment(Comment comment);
    List<Comment> requestComment(Integer blogId);
}
