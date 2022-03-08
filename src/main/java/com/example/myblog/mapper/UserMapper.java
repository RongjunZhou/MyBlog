package com.example.myblog.mapper;

import com.example.myblog.entity.Account;
import com.example.myblog.entity.Blog;
import com.example.myblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    Blog requestBlogByName(String blogName);
    List<Blog> requestBlogByDate();
    Integer addUser(Account account);
    Integer fixPassword(@Param("username") String username, @Param("password") String password);
    Integer addComment(Comment comment);
    Account requestUserByName(String username);
    Integer checkUser(String username);
    Integer countBlog();
    List<Comment> requestComment(Integer blogId);
}
