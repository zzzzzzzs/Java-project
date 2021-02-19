package com.me.Pack002_ClassInitTest;

public class ClassInitTest {
    private static int num = 5;

    static {
        num = 8;
        number = 20;
        System.out.println(num);
        //System.out.println(number);//报错：非法的前向引用。
    }

    private static int number = 10;  //linking之前有prepare阶段: number = 0 --> initial: 20 --> 10
    // 有不清楚的直接看字节码，就很清楚

    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);//2
        System.out.println(ClassInitTest.number);//10
    }
}
