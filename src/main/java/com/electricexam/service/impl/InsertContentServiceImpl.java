package com.electricexam.service.impl;

import com.electricexam.service.IInsertContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: MiaChen
 * @Description: 实现类
 * @Date: 2020/3/25 20:41
 * @Version: 1.0
 */
@Service("iInsertContentService")
public class InsertContentServiceImpl implements IInsertContentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer readExcel2DB(List<String> list) {
        int update = 0;
        for (String s : list) {
            update = jdbcTemplate.update(s);
        }
        return update;
    }
}
