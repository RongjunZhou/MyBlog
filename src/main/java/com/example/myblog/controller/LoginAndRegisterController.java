package com.example.myblog.controller;

import com.example.myblog.Utils.JWTUtils;
import com.example.myblog.entity.Account;
import com.example.myblog.exception.OperationFailException;
import com.example.myblog.service.AdminService;
import com.example.myblog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Validated
@RestController
@CrossOrigin
public class LoginAndRegisterController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@NotBlank String username, @NotBlank String password) {

        //判断用户名是否存在
        if(!userService.checkUser(username))
        {
            throw new OperationFailException(401,"用户名不存在");
        }
        //验证密码是否正确
        Account accountInstance=adminService.checkUserByName(username);
        if((Objects.equals(accountInstance.getPassword(), DigestUtils.sha256Hex(password+DigestUtils.md5Hex(password)))))
        {
            //建立token
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("username",accountInstance.getUsername());
            map.put("email",accountInstance.getEmail());
            map.put("role",accountInstance.getRole());
            return JWTUtils.getToken(map);
        }
        else {
            throw new OperationFailException(403, "密码错误");
        }
    }

    @PostMapping("/register")
    public boolean addUser(@Validated Account account){
        if(userService.checkUser(account.getUsername()))
        {
            throw new OperationFailException(403,"用户名已存在");
        }
        Account accountCase=new Account(account.getUsername(), DigestUtils.sha256Hex(account.getPassword()+DigestUtils.md5Hex(account.getPassword())),account.getEmail(),0);
        return userService.addUser(accountCase);
    }

    @PostMapping("/fixPassword")
    public boolean fixPassword(@NotBlank String username,@NotBlank String originPassword,@NotBlank String newPassword){
        Account originAccount=userService.requestUserByName(username);
        if(!userService.checkUser(username)){
            throw new OperationFailException(403,"用户不存在");
        }
        if(DigestUtils.sha256Hex(originPassword+DigestUtils.md5Hex(originPassword)).equals(originAccount.getPassword())){
            return userService.fixPassword(username,newPassword);
        }
        else{
            throw new OperationFailException(500,"原密码错误");
        }
    }
}
