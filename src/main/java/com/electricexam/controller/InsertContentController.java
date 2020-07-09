package com.electricexam.controller;

import com.electricexam.pojo.AjaxStandardEntity;
import com.electricexam.service.IInsertContentService;
import com.electricexam.util.ReadExcel2DB;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: MiaChen
 * @Description: 将试题插入数据库
 * @Date: 2020/3/25 19:50
 * @Version: 1.0
 */
@Slf4j
@Api(tags = {"将试题导入数据库"})
@Controller
@RequestMapping("/read_excel")
public class InsertContentController {

    @Autowired
    private IInsertContentService iInsertContentService;

    @ApiOperation(value = "导入试题", notes = "两个参数都是必填")
    @ResponseBody
    @PostMapping("/insertData")
    public String readExcelContent(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam("excel文档路径") @RequestParam(value = "excel_path") String excelPath,
                                   @ApiParam("试题类型{有三种-》judge（判断），multiple_selection（多选），single_selection（单选）}") @RequestParam(value = "question_type") String questionType) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ReadExcel2DB readExcel2DB = new ReadExcel2DB();
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = readExcel2DB.readExcel(excelPath, questionType);
        try {
            iInsertContentService.readExcel2DB(list);
            are.setMessage("insert success");
            are.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            String sqlExeMsg = e.getMessage();
            are.setMessage("insert success");
            are.setCode(0);
            are.setData(sqlExeMsg);
        }
        String json = mapper.writeValueAsString(are);
        return json;
    }
}
