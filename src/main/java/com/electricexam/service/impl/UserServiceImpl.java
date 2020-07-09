package com.electricexam.service.impl;

import com.electricexam.dao.IUserDAO;
import com.electricexam.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 用户登录实现类
 * @Date: 2020/3/26 14:34
 * @Version: 1.0
 */
@Slf4j
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDAO iUserDAO;

    @Override
    public int Checklogin(HttpServletRequest request, String username, String password) {
        //判断是否存在改用户名
        List<Map<String, Object>> userList = iUserDAO.getUsername(username);
        if (StringUtils.isEmpty(username)) {
            log.info("您的用户名为空！");
            return 1;
        }
        //如果为空 则用户名不存在
        else if (StringUtils.isEmpty(userList)||userList.size()==0) {
            log.info("该用户名不存在！");
            return 2;
        } else if (StringUtils.isEmpty(password)){
            log.info("密码为空");
            return 4;
        }else {
            //如果存在，则遍历数据，获得pwd
            for (Map<String, Object> stringObjectMap : userList) {
                //判断输入的数据是否和数据库中数据一致
                if (!password.equals(stringObjectMap.get("password"))) {
                    log.info("用户名存在但密码不正确！");
                    //如果相不同则返回2
                    return 3;
                }else{

                    //否则登录成功
                    // 登录成功，将信息写入session
                    request.getSession().setAttribute("username", userList.get(0).get("username") + "");
                    request.getSession().setAttribute("segment", userList.get(0).get("segment") + "");
                    request.getSession().setAttribute("leader", userList.get(0).get("leader") + "");
                    if (username.toLowerCase().startsWith("admin")) {
                        request.getSession().setAttribute("segment", "admin");
                    }
                    request.getSession().setAttribute("login_flag", true);
                }
            }
        }

        return 0;
    }
}
