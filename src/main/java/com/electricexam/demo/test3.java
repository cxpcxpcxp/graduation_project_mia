package com.electricexam.demo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: MiaChen
 * @Description:
 * @Date: 2020/5/19 16:15
 * @Version: 1.0
 */
public class test3 {
    public static void main(String[] args) {
//        String sql = "miatest&&Country&&mias44&&A&&Delivering";
//        String[] split = sql.split("&&");
//        System.out.println(split.length);
//        for (String s : split) {
//            System.out.println(s);
//        }
        IdentityHashMap<String, Object> identityHashMap = new IdentityHashMap<>();
        IdentityHashMap<String, Object> map = new IdentityHashMap<>();
        String[] arr = {"apply_id", "table_name", "opration_type", "sql_string", "current_status", "submit_at", "submit_by", "new_field_list", "old_field_list"};
        identityHashMap.put("qq", "ww");
        identityHashMap.put("ww", 3);
        identityHashMap.put("rr", "tt");
        identityHashMap.put("yy", "uu");
        identityHashMap.put("ii", "oo");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(identityHashMap);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < arr.length; j++) {
                map.put(arr[j], list.get(i).get(arr[j]));
            }

        }

//        for (int i = 0; i < list.size(); i++) {
//            Object dict_label = list.get(i).get(arr[i]);
//            Object count = list.get(i).get(arr[i]);
//            map.put(dict_label.toString(),count);
//        }
//        for(Map map1 : list){
//            Object dict_label = map1.get("qq");
//            Object count = map1.get("ww");
//            map.put(dict_label.toString(),count);
//        }
        Integer all = 0;
        for (Object value : map.values()) {
            all = all + Integer.parseInt(value.toString());
        }
        map.put("all", all);
        System.out.println(all.toString());

    }
}
