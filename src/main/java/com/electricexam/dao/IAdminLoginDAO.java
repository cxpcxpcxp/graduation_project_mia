package com.electricexam.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 管理员登录接口
 * @Date: 2020/3/21 19:36
 * @Version: 1.0
 */
/**
 * @Author: MiaChen
 * @Description: 管理员持久层接口
 * @Date: 2020/3/25 19:50
 * @Version: 1.0
 */
@Repository
public interface IAdminLoginDAO {
    /**
     * 查询所有管理员信息
     * @return
     */
    @Select("select * from userlogin")
    public List<Map<String,Object>> findAllLoginUser();

}
