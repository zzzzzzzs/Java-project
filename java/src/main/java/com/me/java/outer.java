package com.me.java;

/**
 * @author zs
 * @date 2021/10/8.
 * 跳到指定位置的循环
 */
public class outer {
    public static void main(String[] args) {
        outer: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i*10 + j);
                if(j == 5){
                    break outer;
                }
            }
        }
    }
}
