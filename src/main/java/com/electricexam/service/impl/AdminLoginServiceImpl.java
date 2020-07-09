package com.electricexam.service.impl;

import com.electricexam.dao.IAdminLoginDAO;
import com.electricexam.service.IAdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 管理员登录实现类
 * @Date: 2020/3/21 19:39
 * @Version: 1.0
 */
@Service("iAdminLoginService")
public class AdminLoginServiceImpl implements IAdminLoginService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IAdminLoginDAO iAdminLoginDAO;
    @Override
    public List<Map<String, Object>> findAllLoginUser() {
        return iAdminLoginDAO.findAllLoginUser();
    }
    public List<Map<String, Object>> findAllLoginUserByTemplate(){
        String sql = "select * from adminLogin";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
