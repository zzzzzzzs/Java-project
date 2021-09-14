package com.me.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.me.bean.DemoData;
import com.me.bean.JstOrderDetailReportEntity;

/**
 * @author zs
 *@date 2021/8/27.

 */
public class EasyExcelRead {
    public static void main(String[] args) {
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        String fileName = "D:\\workspace\\文档\\销售主题分析\\明细订单\\全平台\\7月份\\销售主题分析_订单(明细商品)_2021-09-13_09-37-52.xlsx";
        // 这里默认读取第一个sheet
//        EasyExcel.read(fileName, DemoData.class, new DemoHeadDataListener()).sheet().doRead();
        long l = System.currentTimeMillis();
        EasyExcel.read(fileName, JstOrderDetailReportEntity.class, new DemoDataListener()).sheet().doRead();
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }
}
