package com.electricexam.service.impl;

import com.electricexam.dao.IItemBankDAO;
import com.electricexam.service.IItemBankService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description:
 * @Date: 2020/3/28 12:39
 * @Version: 1.0
 */
@Slf4j
@Service("iItemBankService")
public class ItemBankServiceImpl implements IItemBankService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IItemBankDAO iItemBankDAO;

    @Override
    public List<Map<String, Object>> getAllItemList(Integer page,Integer pageSize,String questionType) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (StringUtils.isEmpty(questionType)) {
            String sql = "select * from questiontable";
            //        开启分页插件
            PageHelper.startPage(page,pageSize);
            list = jdbcTemplate.queryForList(sql);
        } else {
            //        开启分页插件
            PageHelper.startPage(page,pageSize);
            list = iItemBankDAO.getAllItemList(questionType);
        }
        return list;
    }

    @Override
    public Integer deleteItemByIdList(String[] idList) {
        String sql = "delete from questiontable where id in (";
        for (String integer : idList) {
            sql += integer + ",";
        }
        sql = sql.substring(0, sql.length() - 1) + ")";
        System.out.println(sql);
        Integer integer = jdbcTemplate.update(sql);
        return integer;
    }


    @Override
    public Integer updateItem(Map<String, Object> map, String id) {
        String sql = "update questiontable set ";
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            if (!StringUtils.isEmpty(stringObjectEntry.getValue())) {
                sql += stringObjectEntry.getKey() + "=" + "'" + stringObjectEntry.getValue() + "',";
            }
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += " where id = " + id;
        int update = jdbcTemplate.update(sql);
        return update;
    }

    /**
     * 根据试题名称进行模糊查询 查询出相对应的试题列表
     *
     * @param questionName
     * @return
     */
    @Override
    public List<Map<String, Object>> getItemByQuestionName(Integer page,Integer pageSize,String questionName) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (StringUtils.isEmpty(questionName)) {
            String sql = "select * from questiontable";
            //        开启分页插件
            PageHelper.startPage(page,pageSize);
            list = jdbcTemplate.queryForList(sql);
        } else {
            String sql = "select * from questiontable where ";
            sql += "questionContent like '%" + questionName + "%'";
            log.info("模糊查询--》" + sql);
            //        开启分页插件
            PageHelper.startPage(page,pageSize);
            list = jdbcTemplate.queryForList(sql);
        }
        return list;
    }

    @Override
    public Integer deleteItemById(String s) {
        String sql = "delete from questiontable where id = ?";
        System.out.println(sql);
        int update = jdbcTemplate.update(sql, s);
        return update;
    }

    @Override
    public List<Map<String, Object>> getBothItem(Integer page, Integer pageSize, String questionType, String questionName) {
        String sql = "select * from questiontable where questionType=? and questionContent like '%"+questionName+"%'";
        //        开启分页插件
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, questionType);
        return list;
    }

    /**
     * 正式考试时的列表
     * @return
     */
    @Override
    public List<Map<String, Object>> getExamList() {
        String sql = "select * from questiontable";
        return jdbcTemplate.queryForList(sql);
    }
}
