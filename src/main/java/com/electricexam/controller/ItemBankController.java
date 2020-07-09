package com.electricexam.controller;

import com.electricexam.pojo.AjaxStandardEntity;
import com.electricexam.service.IItemBankService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 试题库控制器
 * @Date: 2020/3/28 11:58
 * @Version: 1.0
 */
@Slf4j
@Api(tags = {"试题库接口"})
@Controller
@RequestMapping(value = "/item_bank")
public class ItemBankController {
    @Autowired
    private IItemBankService iItemBankService;

    /**
     * 获取所有的试题库
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "试题列表", notes = "根据question_type查询。参数：一个非必填的String question_type。" +
            "当question_type为空,查询所有试题。\n" +
            "当question_type=judge,查询所有判断题。\n" +
            "当question_type=multiple_selection,查询所有多选题。\n" +
            "当question_type=ingle_selection,查询所有单选题。\n")
    @ResponseBody
    @GetMapping("/item_list")
    public String getAllItemList(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam("查询item,为空时查询所有，为judge时查询判断题，为multiple_selection时查询多选题，为single_selection时查询单选题。" +
                                         "将judge，multiple_selection，ingle_selection做出下拉选择框，值定死，然后共客户点击选择即可")
                                 @RequestParam(value = "question_type", required = false) String questionType,
                                 @ApiParam("根据试题名称查询试题库，为非必填参数")
                                 @RequestParam(value = "question_name", required = false) String questionName,
                                 @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                 @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = new ArrayList<>();
        //如果questionType为空
        if (StringUtils.isEmpty(questionType)) {
            list = iItemBankService.getItemByQuestionName(page, pageSize, questionName);
            PageInfo pageInfo = new PageInfo(list);
            are.setData(pageInfo);
        }
        //如果questionName为空，查询所有试题，此时questionType默认空，只有当用户点击下拉选择框时才根据questiontype查询
        else if (StringUtils.isEmpty(questionName)) {
            list = iItemBankService.getAllItemList(page, pageSize, questionType);
            PageInfo pageInfo = new PageInfo(list);
            are.setData(pageInfo);
        } else if (!StringUtils.isEmpty(questionType) && !StringUtils.isEmpty(questionName)) {
            list = iItemBankService.getBothItem(page, pageSize, questionType, questionName);
            PageInfo pageInfo = new PageInfo(list);
            are.setData(pageInfo);
        }

        are.setMessage("查询成功");
        String json = mapper.writeValueAsString(are);
        return json;
    }

    /**
     * 操作试题库
     * 增加试题库
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "增加试题库", notes = "##未完成##上传excel文档，增加试题。" +
            "参数--》")
    @ResponseBody
    @PostMapping("/item_list")
    public String addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(are);
        return json;
    }

    /**
     * 操作试题库
     * 删除试题库
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除试题库", notes = "根据id列删除试题,关于变成[90,91]这种形式，在swagger里面测试是这样子的，" +
            "你需要的格式是数组形式，我后台也是数组形式获取的请求参数，所以是一样的，可以按照你需要的格式写，后续响应的结果会是成功的。")
    @ResponseBody
    @DeleteMapping("/item_list")
    public String deleteItem(HttpServletRequest request, HttpServletResponse response,
                             @ApiParam("获取id，id必填")
                             @RequestParam(value = "id_list") String[] idList) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        ObjectMapper mapper = new ObjectMapper();

        if (request.getSession().getAttribute("segment").equals("admin")) {
            try {
                for (String s : idList) {
                    System.out.println(s);
                    iItemBankService.deleteItemById(s);
                }

                are.setMessage("delete data success");
                are.setCode(0);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("e.getMessage()-->" + e.getMessage());
                are.setMessage("sorry,delete data failure");
                are.setCode(500);
            }
        }
        String json = mapper.writeValueAsString(are);
        return json;
    }

    /**
     * 操作试题库
     * 修改 ，订正试题库
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "修改试题库", notes = "根据String id修改，订正试题。" +
            "其他参数都是非必填 共6个字段，1个必填 5个非必填。具体详情请看下方Parameters一栏。Parameter是你需要的字段，Data Type是字段对应的类型")
    @ResponseBody
    @PutMapping("/item_list")
    public String updateItem(HttpServletRequest request, HttpServletResponse response,
                             @ApiParam("获取id，id必填")
                             @RequestParam(value = "id") String id,
                             @ApiParam("获取其他参数，选填 。试题内容")
                             @RequestParam(value = "question_content", required = false) String questionContent,
                             @ApiParam("获取其他参数，选填 。答案")
                             @RequestParam(value = "answer", required = false) String answer,
                             @ApiParam("获取其他参数，选填 。选项A内容")
                             @RequestParam(value = "a", required = false) String AnswerA,
                             @ApiParam("获取其他参数，选填 。选项B内容")
                             @RequestParam(value = "b", required = false) String AnswerB,
                             @ApiParam("获取其他参数，选填 。选项C内容")
                             @RequestParam(value = "c", required = false) String AnswerC) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("questionContent", questionContent);
        map.put("answer", answer);
        map.put("A", AnswerA);
        map.put("B", AnswerB);
        map.put("C", AnswerC);
        if (request.getSession().getAttribute("segment").equals("admin")) {
            try {
                iItemBankService.updateItem(map, id);
                are.setMessage("update data success");
                are.setCode(0);
            } catch (Exception e) {
                e.printStackTrace();
                e.printStackTrace();
                log.info("e.getMessage()-->" + e.getMessage());
                are.setMessage("sorry,update data failure");
                are.setCode(500);
            }
        }
        String json = mapper.writeValueAsString(are);
        return json;
    }

    /**
     * 正式考核的列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/exam_list")
    public String getExamList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, null, null);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = new ArrayList<>();
        list = iItemBankService.getExamList();
        are.setData(list);
        are.setMessage("查询成功");
        String json = mapper.writeValueAsString(are);
        return json;
    }

    @ResponseBody
    @PostMapping(value = "/submit_all")
    public String submitAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, "", "");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(are);
        return json;
    }

    @ResponseBody
    @GetMapping(value = "/option_list")
    public String optionList(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "pageNum") String demo) throws Exception {
        AjaxStandardEntity are = new AjaxStandardEntity(0, "", "");
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        list.add(demo);
        System.out.println("ddddddd");
        System.out.println(list.get(0));
        System.out.println(demo);
        String json = mapper.writeValueAsString(are);
        return json;
    }
}
