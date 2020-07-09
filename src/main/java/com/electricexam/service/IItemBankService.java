package com.electricexam.service;

import java.util.List;
import java.util.Map;

public interface IItemBankService {

    List<Map<String, Object>> getAllItemList(Integer page,Integer pageSize,String questionType);

    Integer deleteItemByIdList(String[] idList);

    Integer updateItem(Map<String, Object> map,String id);

    List<Map<String, Object>> getItemByQuestionName(Integer page,Integer pageSize,String questionName);

    Integer deleteItemById(String s);

    List<Map<String, Object>> getBothItem(Integer page, Integer pageSize, String questionType, String questionName);

    List<Map<String, Object>> getExamList();
}
