package com.electricexam.intercept;

import com.electricexam.pojo.AjaxStandardEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @Author: MiaChen
 * @Description: 管理员登录拦截器
 * @Date: 2020/3/25 22:41
 * @Version: 1.0
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        String urlString = request.getRequestURI();
        //logger.info(" request end with: " + urlString);

        HttpSession session = request.getSession();
        if( urlString.endsWith("/user/login") && !(session.getAttribute("user_role")+"").equals("Internal")  ) { //����Admin�û�
            log.info("User is not Internal, invalid request");
            AjaxStandardEntity are = new AjaxStandardEntity(401,"User is not Internal, invalid request",null);
            ObjectMapper mapper = new ObjectMapper();
            out = response.getWriter();
            out.append(mapper.writeValueAsString(are));
            return false;
        }
        return true;
    }
}
