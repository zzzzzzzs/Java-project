package com.me.DataStructure.tuple;

/**
 * @author zs
 *@date 2021/9/3.

 */
public class TupleTest {
    public static void main(String[] args) {
        Tuple2<String, Integer> tuple2 = Tuple2.of("Hello", 1);
        System.out.println(tuple2);
        String f0 = tuple2.f0;
        Integer f1 = tuple2.f1;
        System.out.println(f0 + "-----" + f1);
    }
}
