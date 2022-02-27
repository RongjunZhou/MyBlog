package com.example.myblog.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResultVO exceptionListener(Exception e){
        return new ResultVO(500,e.toString());
    }

    /**
     *  自定义异常处理
     */
    @ExceptionHandler(OperationFailException.class)
    public ResultVO exceptionListener(OperationFailException e){
        return new ResultVO(e.getCode(),e.getMsg());
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {}

}

