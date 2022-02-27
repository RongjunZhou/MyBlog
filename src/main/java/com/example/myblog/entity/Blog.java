package com.example.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id;
    private String blogName;
    private String blogLocal;
}
