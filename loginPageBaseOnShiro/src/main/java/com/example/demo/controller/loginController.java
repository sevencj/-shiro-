package com.example.demo.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("welcome")
public class loginController {

    // 登录页面跳转
    @GetMapping("login")
    public String login(){
        return "login";
    }


    @PostMapping("login")
    public String login(String username, String password,@RequestParam(defaultValue = "false")boolean rememberMe, HttpSession session) {

        // 1.获取登录认证对象
        Subject subject = SecurityUtils.getSubject();
        // 2.封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 3.调用subject对象的login方法进行认证
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal().toString());
            return "main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "404";
        }


    }

    //登录认证验证rememberMe
    @GetMapping("list")
    @ResponseBody
    public String userLogin(HttpSession session){
//        session.setAttribute("user","rememberMe");
        return "main";
    }


    // 访问后台管理
    @RequiresRoles("admin")
    @GetMapping("backend")
    @ResponseBody
    public String backend(){
            return "backend";
    }


    // 访问用户页面
    @RequiresRoles("user")
    @GetMapping("userPage")
    @ResponseBody
    public String userPage(){
        return "userPage";
    }






}
