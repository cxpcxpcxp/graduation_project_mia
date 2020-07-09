package com.electricexam.controller;

import com.electricexam.pojo.AjaxStandardEntity;
import com.electricexam.service.IExamineeBankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 考生库接口
 * @Date: 2020/3/29 14:04
 * @Version: 1.0
 */
@Api(tags = {"工作人员库接口"})
@Slf4j
@Controller
@RequestMapping(value = "/examiner_bank")
public class ExaminerBankController {
    @Autowired
    private IExamineeBankService iExamineeBankService;

    @ResponseBody
    @GetMapping(value = "/count/ee")
    public String getCountEE(HttpServletRequest request, HttpServletResponse response) throws Exception{
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ObjectMapper mapper = new ObjectMapper();
        int countelectricee = iExamineeBankService.countelectricee();
        are.setData(countelectricee);
        String json = mapper.writeValueAsString(are);
        return json;
    }

}
