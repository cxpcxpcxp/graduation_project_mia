package com.electricexam.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MiaChen
 * @Description: 读取excel文档，将数据插入数据库
 * @Date: 2020/3/25 20:30
 * @Version: 1.0
 */
public class ReadExcel2DB {
    // 避免极小可能出现的同时添加的情况，保证apply id 唯一, 使用synchronized上锁操作
    public synchronized long getNewApplyId() {
        return System.currentTimeMillis();
    }

    public List<String> readExcel(String excelPath, String questionType) throws Exception {
        String sql = null;
        List<String> list = new ArrayList<>();
        FileInputStream inp = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(inp);
        XSSFCell cell = null;
        int flag = 0;
        if (questionType.equals("judge")) {
            XSSFSheet sheet = wb.getSheetAt(1);
            //获取总行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            //循环遍历index，对每个页数进行操作
            for (int j = 0; j < rowNum; j++) {
                sql = "insert into questiontable (id,questionType,questionContent,answer)values(";
                //---------------------1内容----------------------------------------
                //获取工作簿i
                sheet = wb.getSheetAt(0);
                //获取第一页sheet下面  第一行    第一单元格数据
                cell = sheet.getRow(j).getCell(0);
                //将cell转化未字符串
                String stringCellValue0 = cell.getStringCellValue();
                Long id = getNewApplyId() + (flag++);
                sql += id + ",'" + questionType + "','" + stringCellValue0 + "','";
                //---------------------2答案----------------------------------------
                //获取工作簿i
                sheet = wb.getSheetAt(1);
                //获取第一页sheet下面  第一行    第一单元格数据
                cell = sheet.getRow(j).getCell(0);
                //将cell转化未字符串
                String stringCellValue1 = cell.getStringCellValue();
                sql += stringCellValue1 + "','";

                sql = sql.substring(0, sql.length() - 2) + ")";
                System.out.println(sql);
                list.add(sql);
            }
        } else if (questionType.equals("singleSelection")) {
            XSSFSheet sheet = wb.getSheetAt(2);
            //获取总行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            //获取总列数
            int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
            //循环遍历index，对每个页数进行操作
            for (int j = 0; j < rowNum; j++) {
                sql = "insert into questiontable values(";
                //---------------------1内容----------------------------------------
                //获取工作簿i
                sheet = wb.getSheetAt(0);
                //获取第一页sheet下面  第一行    第一单元格数据
                cell = sheet.getRow(j).getCell(0);
                //将cell转化未字符串
                String stringCellValue0 = cell.getStringCellValue();
                Long id = getNewApplyId() + (flag++);
                sql += id + ",'" + questionType + "','" + stringCellValue0 + "','";
                //---------------------2答案----------------------------------------
                //获取工作簿i
                sheet = wb.getSheetAt(1);
                //获取第一页sheet下面  第一行    第一单元格数据
                cell = sheet.getRow(j).getCell(0);
                //将cell转化未字符串
                String stringCellValue1 = cell.getStringCellValue();
                sql += stringCellValue1 + "','";
                //---------------------3选项----------------------------------------
                //获取工作簿i
                sheet = wb.getSheetAt(2);
                for (int i = 0; i < coloumNum; i++) {
                    //获取第三页sheet下面  第一行    第一单元格数据
                    cell = sheet.getRow(j).getCell(i);
                    //将cell转化未字符串
                    String stringCellValue2 = cell.getStringCellValue();
                    sql += stringCellValue2 + "','";
                }
                sql = sql.substring(0, sql.length() - 2) + ")";
                System.out.println(sql);
                list.add(sql);
            }
        }

        return list;
    }
}
