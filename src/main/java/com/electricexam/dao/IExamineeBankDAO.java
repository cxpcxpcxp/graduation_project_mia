package com.electricexam.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface IExamineeBankDAO {
    @Select("select * from userlogin where segment='electricer'")
    List<Map<String, Object>> getAllExamineeList();
    @Select("select count(*) from userlogin")
    int count();

    @Select("select count(*) from userlogin where segment='electricee'")
    int countelectricee();

    @Select("select count(*) from userlogin where segment='electricer'")
    int countelectricer();

    @Select("select count(*) from questiontable ")
    int countExam();
}
