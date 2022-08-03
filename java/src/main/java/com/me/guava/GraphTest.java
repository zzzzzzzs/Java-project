package com.me.guava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.me.bean.Person;

public class GraphTest {
  public static void main(String[] args) {
    MutableGraph<Person> graph1 =
        GraphBuilder.directed() // 指定为有向图
            .nodeOrder(ElementOrder.<Person>insertion()) // 节点按插入顺序输出
            // (还可以取值无序unordered()、节点类型的自然顺序natural())
            //            .expectedNodeCount(4) // 预期节点数
            .allowsSelfLoops(false) // 允许自环
            .build();

    graph1.addNode(new Person("E", 5));
    graph1.addNode(new Person("D", 4));
    graph1.putEdge(new Person("A", 1), new Person("B", 2));

    System.out.println(graph1);
  }
}
