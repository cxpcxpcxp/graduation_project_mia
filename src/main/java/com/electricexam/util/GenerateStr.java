package com.electricexam.util;

import java.util.Random;

/**
 * @Author: MiaChen
 * @Description: 随机生成字符串
 * @Date: 2020/3/29 16:19
 * @Version: 1.0
 */
public class GenerateStr {
    public  String getString(){
        return getRandomString(5)+getNewApplyId();
    }
    public  synchronized long getNewApplyId() {
        return System.currentTimeMillis();
    }
    //length用户要求产生字符串的长度
    public  String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
