package com.example.config;

import com.example.common.Common;
import com.example.entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 * 如果没有登录，重定向到登录页
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 所有访问后台的请求先要从这里路过
     * 返回true就继续执行后面的请求，返回false中断后面的请求，直接转向登录页
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求的路径
        String requestURI = request.getRequestURI();

        // 如果请求的是 register.html 页面，则直接放行
        if ("/end/page/register.html".equals(requestURI)) {
            return true;
        }
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Common.USER_INFO);
        if(userInfo==null){
            //重定向到登录页
            response.sendRedirect("/end/page/login.html");
            return false;
        }
        return true;
    }
}
