package com.electricexam.service;

import java.util.List;
/**
 * @Author: MiaChen
 * @Description: 陆业务接口
 * @Date: 2020/3/21 19:38
 * @Version: 1.0
 */
public interface IInsertContentService {
    /**
     * 将excel 数据插入 数据库
     * @param list
     * @return
     */
    Integer readExcel2DB(List<String> list);
}
