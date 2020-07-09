package com.electricexam.service;

import com.electricexam.dao.IAdminLoginDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 管理员登陆业务接口
 * @Date: 2020/3/21 19:38
 * @Version: 1.0
 */
public interface IAdminLoginService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<Map<String,Object>> findAllLoginUser();

    /**
     * 查询所有用户信息
     * @return
     */
    public List<Map<String, Object>> findAllLoginUserByTemplate();
}
