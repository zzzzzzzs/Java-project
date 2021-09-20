package com.me.utils;

import java.util.Arrays;

/**
 * @author zs
 * @date 2021/9/20.
 * 一些数组的工具
 */
public class ArraysUtils {
    /*
     * @Description 合并数组
     * @Param first
     * @param: second
     * @return
     **/
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
    /**
     * @Description 合并多个数组
     * @Param first
     * @param: rest
     * @return
     **/
    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] int1 = {1, 2, 3};
        Integer[] int2 = {3, 4, 5};
        Integer[] concat1 = concat(int1, int2);
        Arrays.stream(concat1).forEach(System.out::println);
    }
}
