package com.electricexam.demo;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: MiaChen
 * @Description:
 * @Date: 2020/5/18 21:00
 * @Version: 1.0
 */
public class test2 {
    public static void main(String[] args) {
        String insert = "insert into [shared].[sharedProductClassification] values('miatest','Country','mias1','A','Delivering')";
        String delete = "delete from [shared].[sharedProductClassification] where DataSource= 'miatest' and  ProductType= 'Country' and  Company= 'mias44' and  Classification= 'A' and  Status= 'Delivering'";
        String update = "update [shared].[sharedProductClassification]  set  Classification= 'N', status= 'Internal' where  DataSource= 'Card_Payment Share Adjusted' and  ProductType= 'Sector' and  Company= 'Jewelry'";
//        String replace = insert.replace("insert into [shared].[sharedProductClassification] values('", "");
//        String substring = replace.substring(0, replace.length() - 2);
//        String[] split = substring.split("','");
//        String str = "";
//        for (int i = 0; i < split.length; i++) {
//            str+=split[i]+"&&";
//        }
//        System.out.println(str);


//        String replace = delete.replace("delete from [shared].[sharedProductClassification] where ", "");
//        System.out.println(replace);
//        String regEx = "[',]";
//        String aa = "";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(replace);//这里把想要替换的字符串传进来
//        String newString = m.replaceAll(aa).trim();
//        String newString2 = newString.replace(" and  ",",");
//        System.out.println(newString);
//        System.out.println(newString2);
//
//        String[] s = newString2.split(",");
//        for (String s1 : s) {
//            String ss1=s1.substring(0, s1.indexOf("= "));
//            String ss2=s1.substring(ss1.length()+2, s1.length());
//            System.out.println(ss2);
//        }

        /////delete2////
//        System.out.println("delete...");
//        String regEx = "[',]";
//        String replace = "";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(replace);// 这里把想要替换的字符串传进来
//        String newString = m.replaceAll(replace).trim();
//        newString = newString.replace(" and  ", ",");
//
//        String[] s = newString.split(",");
//        for (int i = 0; i < s.length; i++) {
//            String ss1 = s[i].substring(0, s[i].indexOf("= "));
//            String ss2 = s[i].substring(ss1.length() + 2, s[i].length());
//            tempStr += ss2 + "&&";
//        }
//        tempStr = tempStr.substring(0, tempStr.length() - 2);
//        System.out.println("delete_sql->" + tempStr);

//        String replace = update.replace("update [shared].[sharedProductClassification]  set  ", "");
//        System.out.println(replace);
//        String substring = replace.substring(0, replace.indexOf("where"));
//        System.out.println(substring);
//        substring = substring.replace("', ", ",");
//        String regEx = "[']";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(substring);//这里把想要替换的字符串传进来
//        String newString = m.replaceAll("").trim();
//        newString = newString.replace(" ", "");
//        String[] split = newString.split(",");
//        for (String s : split) {
//            String ss1=s.substring(0, s.indexOf("="));
//            String ss2=s.substring(ss1.length()+1, s.length());
//            System.out.println(ss2);
//        }
//        String originalTemp = replace.substring(replace.indexOf("where "), replace.length());
//        System.out.println(originalTemp);
//        replace = replace.substring(replace.indexOf("where ")+7, replace.length());
//        replace = replace.replace("' and  ", ",");
//        replace = replace.replace(" '", "");
//        replace = replace.substring(0,replace.length()-1);
//        System.out.println(replace);
//        String[] split1 = replace.split(",");
//        for (int i = 0; i < split1.length; i++) {
//            String ss1=split1[i].substring(0, split1[i].indexOf("="));
//            String ss2=split1[i].substring(ss1.length()+1, split1[i].length());
//            System.out.println(ss2);
//        }

//        List<Map<String, Object>> originalList = null;
//        originalList.get(0).get("DataSource");
//        Object productType = originalList.get(0).get("ProductType");
//        originalList.get(0).get("Company");
//        originalList.get(0).get("Classification");
//        originalList.get(0).get("Status");
    }
}
