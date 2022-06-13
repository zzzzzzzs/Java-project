package com.me.guava;

import com.google.common.collect.HashBasedTable;

public class TableTest {
  public static void main(String[] args) {
    // 行，列，值
    HashBasedTable<Object, Object, Object> table = HashBasedTable.create();
    table.put("qa_customer_answer", null, "1");
    System.out.println(table);
  }
}
