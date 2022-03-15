package com.example.myblog.entity;

public class Result {

    public int status;
    public String message;
    public Object data;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Object data) {
        this.status = 200;
        this.message = "success";
        this.data = data;
    }
}
