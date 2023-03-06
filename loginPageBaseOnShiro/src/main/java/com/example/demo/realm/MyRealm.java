package com.example.demo.realm;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 自定义身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.获取前端输入的用户信息
        String username =  authenticationToken.getPrincipal().toString();
        // 2.调用业务层获取数据库用户信息
        UserEntity userInfo = userService.getUserInfo(username);
        // 3.非空判断，将数据封装返回
        if(userInfo!=null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    userInfo.getPassword(),
                    username
            );
            return info;
        }



        return null;
    }
}
