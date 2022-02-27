package com.example.myblog.exception;

public class ResultVO {

    public int status;
    public String message;
    public Object data;

    public ResultVO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultVO(Object data) {
        this.status = 200;
        this.message = "success";
        this.data = data;
    }
}
