package com.example.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @NotBlank
    private Integer id;
    private String Commenter;
    @NotBlank
    private String CommentValue;
}
