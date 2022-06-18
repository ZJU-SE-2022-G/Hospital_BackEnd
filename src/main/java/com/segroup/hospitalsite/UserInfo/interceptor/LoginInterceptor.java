package com.segroup.hospitalsite.UserInfo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象当周是否有uid数据，如果有则放行，如果没有则重定向到登录节目
     * @param request 请求对象
     * @param response 响应对象
     * @param handler  处理器（url+controller 映射）
     * @return true则放行请求，false则表示拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // HttpServletRequest来获取session对象
        Object uidObj= request.getSession().getAttribute("uid");
        if(uidObj == null){ // 说明没有登录，重定向到login.html
            response.sendRedirect("/web/login.html");  // 这里要替换成实际登录页面路径
            System.out.println("[Login Interceptor]: request intercepted");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
