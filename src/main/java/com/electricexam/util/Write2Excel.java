package com.electricexam.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: MiaChen
 * @Description: 写数据到excel
 * @Date: 2020/3/25 21:09
 * @Version: 1.0
 */
public class Write2Excel {
    public static void main(String[] args) {
        writeData();
    }

    public static void writeData() {
        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;

        //写入数据
        for (int i = 0; i <= 10; i++) {
            HSSFRow nrow = sheet.createRow(i);
            HSSFCell ncell = nrow.createCell(0);
            ncell.setCellValue("" + i);
            ncell = nrow.createCell(1);
            ncell.setCellValue("user" + i);
            ncell = nrow.createCell(2);
            ncell.setCellValue("24");
        }
        //创建excel文件
        File file = new File("D:\\poi.xlsx");
        try {
            file.createNewFile();
            //将excel写入
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
