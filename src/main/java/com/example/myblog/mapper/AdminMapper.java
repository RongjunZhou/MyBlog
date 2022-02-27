package com.example.myblog.mapper;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {
    Account checkUserByName(String username);
    Integer deleteUserByName(String username);
    Blog checkBlogByName(String blogName);
    Integer deleteBlogByName(String blogName);
    Integer addBlog(Blog blog);
    Integer addPicture(Picture picture);
}
