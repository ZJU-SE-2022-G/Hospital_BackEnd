package com.segroup.hospitalsite.UserInfo.config;

import com.segroup.hospitalsite.UserInfo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

// 注册处理器和拦截器
@Configuration
public class LoginInterceptorConfigurator implements WebMvcConfigurer {
    // 创建拦截器对象
    HandlerInterceptor loginInterceptor = new LoginInterceptor();



    @Override
    // 注册自定义拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建白名单
        List<String> list = new ArrayList<>();
        // 可以不登录使用的网页
        list.add("/web/login.html");
        list.add("/web/register.html");

        // 网页资源
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");

        // url请求
        list.add("/users/reg");
        list.add("/users/login");
        list.add("/users/loginByPhone");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")  // 拦截所有的url
                .excludePathPatterns(list);  // 除了list里面的url，这里按需求填
    }
}
