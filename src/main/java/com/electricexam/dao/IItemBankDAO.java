package com.electricexam.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface IItemBankDAO {
    @Select({"<script> " +
            "select * from questiontable "+
            "where  1=1 " +
            "<if test='questionType!=null'> " +
            "and questionType = #{questionType}</if> "+
            "</script>"})
    List<Map<String, Object>> getAllItemList(@Param(value = "questionType") String questionType);

}
