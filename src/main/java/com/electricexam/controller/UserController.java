package com.electricexam.controller;

import com.electricexam.pojo.AjaxStandardEntity;
import com.electricexam.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import static com.electricexam.controller.LockTest.f;

/**
 * @Author: MiaChen
 * @Description: 用户登录 访问首页控制器
 * @Date: 2020/3/26 14:25
 * @Version: 1.0
 */
@Api(tags = {"用户登录接口"})
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录接口
     *
     * @param request
     * @param response
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "登录", notes = "两个参数都是必填")
    @ResponseBody
    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public String HelloWorld(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "username", required = true) String username,
                             @RequestParam(value = "password", required = true) String password) throws Exception {

        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        ObjectMapper mapper = new ObjectMapper();
        int checklogin = iUserService.Checklogin(request, username, password);
        //用户不存在
        if (checklogin == 1) {
            are.setCode(401);
            are.setMessage("你的用户名为空");
        } else if (checklogin == 2) {//密码不正确
            are.setCode(401);
            are.setMessage("用户名不存在");
        } else if (checklogin == 3) {
            are.setCode(401);
            are.setMessage("密码不正确，如果您想重置密码，请通知管理员");
        } else if (checklogin == 4) {
            are.setCode(401);
            are.setMessage("密码为空");
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", username);
            map.put("segment", request.getSession().getAttribute("segment") + "");
            map.put("login_flag", request.getSession().getAttribute("login_flag") + "");
            map.put("leader", request.getSession().getAttribute("leader") + "");
            are.setData(map);
            are.setMessage("登陆成功");
        }
        String json = mapper.writeValueAsString(are);
        log.info(username + " login " + json);
        return json;
    }

    /**
     * 退出接口
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "退出", notes = "无")
    @ResponseBody
    @DeleteMapping(value = "/logout", produces = "application/json; charset=utf-8")
    public String logout(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        log.info(" ----logout success");
        HttpSession session = request.getSession();
        session.invalidate();
        are.setMessage("成功退出登录...");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(are);
        log.info("logout-->" + json);
        return json;
    }

    /**
     * 退出接口
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "过滤", notes = "无")
    @ResponseBody
    @PostMapping(value = "/intercept", produces = "application/json; charset=utf-8")
    public String intercept(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        HttpSession session = request.getSession();
        String segment = (String) session.getAttribute("segment");
        if (segment.equals("admin")) {
            are.setCode(0);
        } else if (segment.equals("electricer")) {
            are.setCode(1);
        } else if (segment.equals("electricee")) {
            are.setCode(2);
        }
        are.setMessage("过滤");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(are);
        log.info("logout-->" + json);
        return json;
    }


    @ApiOperation(value = "过滤", notes = "无")
    @ResponseBody
    @GetMapping(value = "/t", produces = "application/json; charset=utf-8")
    public String test(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        are.setMessage("测试");
//        f();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(are);
        log.info("logout-->" + json);
        return json;
    }
}
