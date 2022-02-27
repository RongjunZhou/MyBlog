package com.example.myblog.service.impl;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Comment;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;

    @Override
    public Account requestUserByName(String username) {
        return usermapper.requestUserByName(username);
    }

    @Override
    public Blog requestBlogByName(String blogName) {
        return usermapper.requestBlogByName(blogName);
    }

    @Override
    public Blog requestBlogByDate(Integer count) {
        return usermapper.requestBlogByDate(count);
    }

    @Override
    public boolean checkUser(String username) {
        return usermapper.checkUser(username)>0;
    }

    @Override
    public boolean addUser(Account account) {
        return usermapper.addUser(account)>0;
    }

    @Override
    public boolean fixPassword(String username, String password) {
        return usermapper.fixPassword(username,password)>0;
    }

    @Override
    public boolean addComment(Comment comment) {
        return usermapper.addComment(comment)>0;
    }
}
