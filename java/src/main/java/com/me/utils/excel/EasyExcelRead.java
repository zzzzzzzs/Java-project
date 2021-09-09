package com.me.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.me.bean.DemoData;

/**
 * @author zs
 *@date 2021/8/27.

 */
public class EasyExcelRead {
    public static void main(String[] args) {
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        String fileName = "C:\\Users\\simeitol\\Desktop\\销售主题分析_订单(明细商品)_2021-09-08_11-15-51.xlsx";
        // 这里默认读取第一个sheet
//        EasyExcel.read(fileName, DemoData.class, new DemoHeadDataListener()).sheet().doRead();
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
