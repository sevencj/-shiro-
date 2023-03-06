package com.example.demo.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("welcome")
public class loginController {




    @GetMapping("login")
    public String login(String username, String password){

        // 1.获取登录认证对象
        Subject subject = SecurityUtils.getSubject();
        // 2.封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        // 3.调用subject对象的login方法进行认证
        try {
            subject.login(token);
            return "login successful";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login failed";
        }


    }


}
