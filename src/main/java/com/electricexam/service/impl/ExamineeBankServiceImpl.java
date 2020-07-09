package com.electricexam.service.impl;

import com.electricexam.dao.IExamineeBankDAO;
import com.electricexam.service.IExamineeBankService;
import com.electricexam.util.GenerateStr;
import com.electricexam.util.PageBean;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description: 实现类
 * @Date: 2020/3/29 14:22
 * @Version: 1.0
 */
@Slf4j
@Service("iExamineeBankService")
public class ExamineeBankServiceImpl implements IExamineeBankService {
    GenerateStr generateStr = new GenerateStr();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IExamineeBankDAO iExamineeBankDAO;

    @Override
    public List<Map<String, Object>> getAllExamineeList(Integer page,Integer limit) {
        //        开启分页插件
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> allExamineeList = iExamineeBankDAO.getAllExamineeList();
        return allExamineeList;
    }

    @Override
    public List<Map<String, Object>> getPartExamineeList(Integer page ,Integer limit,String username) {
        String[] split = username.split("[.]");
        String name = split[1];
        System.out.println("简称--》" + name);
        //不能查询到密码
        String sql = "select id,username,segment,leader from userlogin where leader= '" + name + "'";
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getOneExamineeList(Integer page,Integer limit,String examineeName) {
        String sql = "select * from userlogin where username like '%" + examineeName + "%'";
        //        开启分页插件
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        int countNums = iExamineeBankDAO.count();            //总记录数
//        PageBean<Map<String, Object>> pageData = new PageBean<>(page, limit, countNums);
//        pageData.setItems(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> getOnePartExamineeList(Integer page,Integer limit,String examineeName) {
        String sql = "select id ,username,segment,leader from userlogin where username like '%" + examineeName + "%'";
        //        开启分页插件
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getLeaderByName(String examineeName) {
        String sql = "select leader from userlogin where username like '%" + examineeName + "%'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public Integer addExaminee(Integer number, String segment, String username) {
        String password = "AsdfZxcv";//固定值
        String psegement = segment;//固定值
        String pleader = null;
        if (!username.equals("admin")) {
            String[] split = username.split("[.]");
            pleader = split[1];//固定值
        } else {
            pleader = username;//固定值
        }
        Integer integer = 0;
        for (int i = 0; i < number; i++) {
            String sql = "insert into userlogin (username,password,segment,leader) values (";
            String name = "electricee.";
            name += generateStr.getString() + ".com";
            log.info("随机生成的用户名为--》" + name);
            sql += "'" + name + "','" + password + "','" + psegement + "','" + pleader + "')";
            log.info("这个sql为--》" + sql);
            try {
                integer = jdbcTemplate.update(sql);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        return integer;
    }

    @Override
    public int countelectricee() {
        return iExamineeBankDAO.countelectricee();
    }

    @Override
    public int countelectricer() {
        return iExamineeBankDAO.countelectricer();
    }

    @Override
    public int countExam() {
        return iExamineeBankDAO.countExam();
    }
}
