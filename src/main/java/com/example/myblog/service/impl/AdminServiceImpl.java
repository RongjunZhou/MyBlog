package com.example.myblog.service.impl;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Picture;
import com.example.myblog.mapper.AdminMapper;
import com.example.myblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Account checkUserByName(String username) {
        return adminMapper.checkUserByName(username);
    }

    @Override
    public boolean deleteUserByName(String username) {
        return adminMapper.deleteUserByName(username)>0;
    }

    @Override
    public Blog checkBlogByName(String blogName) {
        return adminMapper.checkBlogByName(blogName);
    }

    @Override
    public boolean deleteBlogByName(String blogName) {
        return adminMapper.deleteBlogByName(blogName)>0;
    }

    @Override
    public boolean addBlog(Blog blog) {
        return adminMapper.addBlog(blog)>0;
    }

    @Override
    public boolean addPicture(Picture picture) {
        return adminMapper.addPicture(picture)>0;
    }
}
