package com.example.controller;


import cn.hutool.core.util.StrUtil;
import com.example.common.Common;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.UserInfo;
import com.example.exception.CustomException;
import com.example.service.UserInfoService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录，退出相关的控制器
 */
@RestController
public class AccountController {
    @Resource
    private UserInfoService userInfoService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody UserInfo userInfo, HttpServletRequest request){
        if(StrUtil.isBlank(userInfo.getName())||StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        // 从数据库查询账号密码是否正确，放到session
        UserInfo login = userInfoService.login(userInfo.getName(),userInfo.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(Common.USER_INFO,login);
        session.setMaxInactiveInterval(60*24*60);
        return Result.success(login);
    }

    /**
     * 重置密码为123456
     */
    @PostMapping("/resetPassword")
    public Result<UserInfo> resetPassword(@RequestBody UserInfo userInfo){
        return Result.success(userInfoService.resetPassword(userInfo.getName()));
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().setAttribute(Common.USER_INFO,null);
        return Result.success();
    }

    /**
     * 小程序端用户注册
     */
    @PostMapping("/register")
    public Result<UserInfo> register(@RequestBody UserInfo userInfo,HttpServletRequest request){
        if(StrUtil.isBlank(userInfo.getName())||StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        UserInfo register = userInfoService.add(userInfo);
        HttpSession session = request.getSession();
        session.setAttribute(Common.USER_INFO,register);
        session.setMaxInactiveInterval(60*24*60);
        return Result.success(register);
    }

    /**
     * 判断用户是否已登录
     */
    @GetMapping("/auth")
    public Result getAuth(HttpServletRequest request){
        Object user = request.getSession().getAttribute(Common.USER_INFO);
        if(user == null){
            return Result.error("401","未登录");
        }
        return Result.success(user);
    }
}
