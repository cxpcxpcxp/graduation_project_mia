package com.electricexam.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
public interface IUserDAO {
    /**
     * 从数据库中查询对应的用户
     * @param username
     * @return
     */
    @Select("select * from userlogin where username=#{username}")
    List<Map<String, Object>> getUsername(@Param(value = "username") String username);
}
