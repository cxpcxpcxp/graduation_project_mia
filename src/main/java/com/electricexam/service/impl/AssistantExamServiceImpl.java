package com.electricexam.service.impl;

import com.electricexam.service.IAssistantExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: MiaChen
 * @Description:
 * @Date: 2020/4/25 18:40
 * @Version: 1.0
 */
@Service("iAssistantExamService")
public class AssistantExamServiceImpl implements IAssistantExamService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String, Object>> list() {
        String sql = "select * from assistantexam";
        return jdbcTemplate.queryForList(sql);
    }
}
