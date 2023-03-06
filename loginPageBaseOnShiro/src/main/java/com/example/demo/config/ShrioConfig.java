package com.example.demo.config;


import com.example.demo.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShrioConfig {

    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        // 1. 创建defaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        // 2. 创建加密对象，设置相关属性
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        // 2.1 采用md5加密
//        matcher.setHashAlgorithmName("md5");
//        // 2.2 迭代加密次数
//        matcher.setHashIterations(3);
//        // 3.将加密对象存储到myRealm中
//        myRealm.setCredentialsMatcher(matcher);
        // 4.将myRealm存入defaultWebSecurityManager对象
        defaultWebSecurityManager.setRealm(myRealm);
        // 5.返回
        return  defaultWebSecurityManager;

    }


    // 配置Shiro内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){

        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 设置不认证可以访问的资源
        definition.addPathDefinition("/welcome","anon");
        // 设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**","authc");
        return definition;
    }


}
