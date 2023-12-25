package com.me.DataStructure.PriorityQueueManager;

import java.util.List;

public class PriorityQueueManagerTest {


    public static void main(String[] args) {
        // 测试代码
        PriorityQueueManager qd = new PriorityQueueManager();
        qd.addQuery("001", 1, "某低优先级数据");
        qd.addQuery("001", 3, "某高优先级数据");
        qd.addQuery("002", 2, "某中优先级数据");

        System.out.println(qd.getTopQueryById("001")); // 输出：某高优先级数据
        System.out.println(qd.getTopQueryById("002")); // 输出：某中优先级数据

        List<String> queriesDataByKey = qd.getQueriesDataByKey("001");
        queriesDataByKey.forEach(System.out::println);

        qd.removeQueryById("001");
        System.out.println(qd.getTopQueryById("001")); // 输出：某高优先级数据
    }
}
