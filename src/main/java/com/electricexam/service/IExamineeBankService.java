package com.electricexam.service;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface IExamineeBankService {
    List<Map<String,Object>> getAllExamineeList(Integer page,Integer limit);

    List<Map<String, Object>> getPartExamineeList(Integer page,Integer limit,String username);

    List<Map<String, Object>> getOneExamineeList(Integer page,Integer limit,String examineeName);

    List<Map<String, Object>> getOnePartExamineeList(Integer page,Integer limit,String examineeName);

    List<Map<String, Object>> getLeaderByName(String examineeName);

    Integer addExaminee(Integer number,String segment,String username);
    int countelectricee();
    int countelectricer();
    int countExam();
}
