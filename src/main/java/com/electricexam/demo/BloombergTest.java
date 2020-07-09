package com.electricexam.demo;

import java.util.IdentityHashMap;

/**
 * @Author: MiaChen
 * @Description:
 * @Date: 2020/5/20 15:21
 * @Version: 1.0
 */
public class BloombergTest {
    public static void main(String[] args) {


        String insert = "insert into [shared].[sharedCompanyBloombergTickerReference] (BloombergTicker,Company,Sector,SWASectorGroup) values('2081 HK Equity','miatest','N/A',null)";
        String delete = "delete from [shared].[sharedCompanyBloombergTickerReference] where Id=4158";
        String update = "update [shared].[sharedCompanyBloombergTickerReference] set BloombergTicker=null,SWASector='Food, Dairy & Beverage',SWASectorGroup='Consumer' where Id=2574";

        //增加字符串切割
        String replace = insert.replace("insert into [shared].[sharedCompanyBloombergTickerReference] (BloombergTicker,Company,Sector,SWASectorGroup) values('", "");
        replace = replace.substring(0,replace.length()-1);
        System.out.println(replace);
        String[] split = replace.split("','");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        //删除字符串切割
        //修改字符串切割

    }
}
