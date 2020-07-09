package com.electricexam.intercept;

import com.electricexam.pojo.AjaxStandardEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @Author: MiaChen
 * @Description: 拦截器配置
 * @Date: 2020/3/21 18:54
 * @Version: 1.0
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 基于URL实现的拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        String urlString = request.getRequestURI();
        log.info("LoginInterceptor : " + urlString + " : " + request);
        HttpSession session = request.getSession();

    	/*if(!urlString.contains("/user/login") && session.getAttribute("login_flag") == null) {
    	    log.info("User not login, please login");
    		AjaxStandardEntity are = new AjaxStandardEntity(401,null,null);
    		ObjectMapper mapper = new ObjectMapper();
    		out = response.getWriter();
    		out.append(mapper.writeValueAsString(are));
    		return false;
    	}*/

        return true;
    }
}