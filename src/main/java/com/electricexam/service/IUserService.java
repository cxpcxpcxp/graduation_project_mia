package com.electricexam.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: MiaChen
 * @Description: 用户登录接口
 * @Date: 2020/3/26 14:25
 * @Version: 1.0
 */
public interface IUserService {
    public int Checklogin(HttpServletRequest request, String username, String password);
}
