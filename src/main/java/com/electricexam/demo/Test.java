package com.electricexam.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * @Author: MiaChen
 * @Description: 测试
 * @Date: 2020/3/24 11:38
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String str = "update [shared].[sharedProductClassification]  set  Classification= 'N', status= 'Internal' where  DataSource= 'Card_Payment Share Adjusted' and  ProductType= 'Sector' and  Company= 'Jewelry'";
        //获得第一个点的位置
        int index = str.indexOf("where");

        String result = str.substring(index);
//        String substring = result.substring(6, result.length());
//        System.out.println(substring);
        //输出结果
        System.out.println(result);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {

            }
        }
    IdentityHashMap<String ,Object> m = new IdentityHashMap<>();

        System.out.println("=================================");
        String sqlValueString = "update [shared].[sharedProductClassification]  set  Classification= 'N', status= 'Internal' where  DataSource= 'Card_Payment Share Adjusted' and  ProductType= 'Sector' and  Company= 'Jewelry'";
        sqlValueString = sqlValueString
                .replace("update [shared].[sharedProductClassification]  set  ", "");
        System.out.println(sqlValueString);
        sqlValueString = sqlValueString.substring(0, sqlValueString.length() - 1);
        System.out.println(sqlValueString);
        String strFront = sqlValueString.substring(0, sqlValueString.indexOf(" where"));

        String str2=sqlValueString.substring(strFront.length()+1, sqlValueString.length());
        System.out.println(strFront);
        System.out.println(str2);
        String where__ = str2.replace("where  ", "");
        System.out.println(where__);
    }
}
