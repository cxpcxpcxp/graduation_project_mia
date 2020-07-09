package com.electricexam.controller;

import com.electricexam.pojo.AjaxStandardEntity;
import com.electricexam.service.IAssistantExamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 随即考核页面
 * @Date: 2020/4/25 18:36
 * @Version: 1.0
 */
@Slf4j
@Controller
@RequestMapping(value = "/assistant")
public class AssistantExamController {
    @Autowired
    private IAssistantExamService iAssistantExamService;
    @ResponseBody
    @GetMapping(value = "/list")
    public String getAssistList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> list = iAssistantExamService.list();
        are.setData(list);
        String json = mapper.writeValueAsString(are);
        return json;
    }

    @ResponseBody
    @PostMapping(value = "/submit_all")
    public String submitAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(are);
        return json;
    }
}
