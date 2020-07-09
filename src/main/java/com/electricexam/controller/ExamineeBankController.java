package com.electricexam.controller;

import com.electricexam.pojo.AjaxStandardEntity;
import com.electricexam.service.IExamineeBankService;
import com.electricexam.util.PageBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
@Api(tags = {"考生库接口"})
@Slf4j
@Controller
@RequestMapping(value = "/examinee_bank")
public class ExamineeBankController {
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
    @ResponseBody
    @GetMapping(value = "/count/er")
    public String getCountER(HttpServletRequest request, HttpServletResponse response) throws Exception{
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ObjectMapper mapper = new ObjectMapper();
        int countelectricer = iExamineeBankService.countelectricer();
        are.setData(countelectricer);
        String json = mapper.writeValueAsString(are);
        return json;
    }
    @ResponseBody
    @GetMapping(value = "/count/exam")
    public String getCountExam(HttpServletRequest request, HttpServletResponse response) throws Exception{
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ObjectMapper mapper = new ObjectMapper();
        int i = iExamineeBankService.countExam();
        are.setData(i);
        String json = mapper.writeValueAsString(are);
        return json;
    }
    @ApiOperation(value = "查询考生库列表", notes = "查询考生信息 只有一个参数 String examinee_name 非必填。")
    @ResponseBody
    @GetMapping(value = "/examinee_list")
    public String getAllExamineeList(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "examinee_name", required = false) String examineeName,
                                           @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                           @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0,"","");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = new ArrayList<>();
        String segment = (String) request.getSession().getAttribute("segment");
        if (segment.equals("admin")) {
            //examineeName不为空
            if (!StringUtils.isEmpty(examineeName)) {
                list = iExamineeBankService.getOneExamineeList(page, pageSize, examineeName);
                log.info("examineeName不为空分页。。。");
                PageInfo pageInfo = new PageInfo(list);
                are.setData(pageInfo);
            }
            // examineeName为空
            else {
                //如果是admin查询所有
                list = iExamineeBankService.getAllExamineeList(page, pageSize);
                log.info("examineeName为空admin查询所有分页");
                PageInfo pageInfo = new PageInfo(list);
                are.setData(pageInfo);
            }

        } else if (segment.equals("electricer")) {
            //examineeName不为空
            if (!StringUtils.isEmpty(examineeName)) {
                //根据examinneName查询到对应的leader,判断是否此工作人员可以查询
                List<Map<String, Object>> leader = iExamineeBankService.getLeaderByName(examineeName);
                String leader1 = (String) leader.get(0).get("leader");
                String user = (String) request.getSession().getAttribute("username");
                String[] split = user.split("[.]");
                String user1 = split[1];
                log.info("user-->" + user1);
                log.info("leader-->" + leader1);
                if (!user1.equals(leader1)) {
                } else {
                    //工作人员只可以查询当前考生除密码之外的所有信息所有
                    list = iExamineeBankService.getOnePartExamineeList(page, pageSize, examineeName);
                    PageInfo pageInfo = new PageInfo(list);
                }
            }
            // examineeName为空
            else {
                String username = (String) request.getSession().getAttribute("username");
                //如果是工作人员查询对应的考生
                list = iExamineeBankService.getPartExamineeList(page, pageSize, username);
                PageInfo pageInfo = new PageInfo(list);
            }
        }
        are.setMessage("查询成功");
        String json = mapper.writeValueAsString(are);
        return json;
    }

    @ApiOperation(value = "增加考生", notes = "工作人员提交增加考生的数量 Int类型字段 number 。必填项。自动生成指定数量的账户。然后将生成的账户，生成excel表格，返回给工作人员，供工作人员使用。")
    @ResponseBody
    @PostMapping(value = "/examinee_list")
    public String addExaminee(HttpServletRequest request, HttpServletResponse response,
                              @ApiParam("需要增加的考生账户数量。" +
                                      "增加成功后可以访问--》http://127.0.0.1:8801/examinee_bank/examinee_list。" +
                                      "会发现此用户下的考生列表多了很多" +
                                      "账户初始密码均为--》AsdfZxcv")
                              @RequestParam(value = "number") Integer number) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, "", "");
        ObjectMapper mapper = new ObjectMapper();
        String username = (String) request.getSession().getAttribute("username");
        String segment = (String) request.getSession().getAttribute("segment");
        if (segment.equals("admin") || segment.equals("electricer")) {
            try {
                iExamineeBankService.addExaminee(number, segment, username);
                are.setMessage("恭喜你，成功添加数据");
                are.setCode(0);
            } catch (Exception e) {
                e.printStackTrace();
                are.setMessage("很遗憾，添加数据失败");
                are.setCode(500);
            }
        } else {
            are.setMessage("你暂时没有删除考生的权限");
            are.setCode(403);
        }
        String json = mapper.writeValueAsString(are);
        return json;
    }

    @ApiOperation(value = "删除考生", notes = "可以单个删除 批量删除 全选删除")
    @ResponseBody
    @DeleteMapping(value = "/examinee_list")
    public String deleteExaminee(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam("String [] 类型 id_list")
                                 @RequestParam(value = "id_list") String[] idList) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, "", "");
        ObjectMapper mapper = new ObjectMapper();
        are.setMessage("接口还未写,只提供了参数");
        String json = mapper.writeValueAsString(are);
        return json;
    }

    @ApiOperation(value = "修改考生信息", notes = "只允许管理员修改信息，一般只修改密码")
    @ResponseBody
    @PutMapping(value = "/examinee_list")
    public String updateExaminee(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam("id,为必填项")
                                 @RequestParam(value = "id") String id,
                                 @ApiParam("必填项")
                                 @RequestParam(value = "password") String password) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, "", "");
        ObjectMapper mapper = new ObjectMapper();
        are.setMessage("接口还未写,只提供了参数");
        String json = mapper.writeValueAsString(are);
        return json;
    }
}
