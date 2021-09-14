package com.me.utils.excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;

/**
 * @author zs
 *@date 2021/9/13.

 */
public class HutoolExcel {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\simeitol\\Desktop\\销售主题分析_订单(明细商品)_2021-09-13_11-27-39.xlsx");
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll.toString());
    }
}
