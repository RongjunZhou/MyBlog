package com.example.myblog.service.impl;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Comment;
import com.example.myblog.mapper.UserMapper;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Blog> requestBlogByDate(Integer page) {
        //查询全部数据
        List<Blog> blogs = usermapper.requestBlogByDate();
        //从第几条数据开始
        int firstIndex = (page-1)*5;
        //到第几条数据结束
        int lastIndex =page*5-1;
        if(lastIndex>usermapper.countBlog()){
            lastIndex=usermapper.countBlog();
        }
        return blogs.subList(firstIndex,lastIndex); //直接在list中截取
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

    @Override
    public List<Comment> requestComment(Integer blogId){
        return usermapper.requestComment(blogId);
    }
}
