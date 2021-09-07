package com.me.utils.simudata;

import java.util.Random;

/**
 * @author zs
 *@date 2021/8/17.
模拟生成数字
 */
public class SimuNum {
    public static final int getRandInt(int fromNum, int toNum) {
        return fromNum + new Random().nextInt(toNum - fromNum + 1);
    }

    public static final int getRandInt(int fromNum, int toNum, Long seed) {
        return fromNum + new Random(seed).nextInt(toNum - fromNum + 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 60; i++) {
            int randInt = getRandInt(0, 1);
            System.out.println("模拟数字：" + randInt);
        }
    }
}
