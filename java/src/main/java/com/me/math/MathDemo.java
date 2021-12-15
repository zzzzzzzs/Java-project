package com.me.math;


import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.fraction.Fraction;

import java.util.Comparator;

/**
 * @author zs
 * @date 2021/12/10
 */
public class MathDemo {
    public static void main(String[] args) {
        Fraction lhs = new Fraction(1, 3);
        int compare = NumberUtils.compare(1, 2);
        int compare1 = Double.compare(lhs.doubleValue(), 1.0);
        System.out.println(lhs.doubleValue());
    }
}
