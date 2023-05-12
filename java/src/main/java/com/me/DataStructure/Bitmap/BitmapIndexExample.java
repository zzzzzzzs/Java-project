package com.me.DataStructure.Bitmap;

import java.util.BitSet;

public class BitmapIndexExample {
    public static void main(String[] args) {
        // 创建一个表格，包含 id、gender、age 三列
        int[] id = {1, 2, 3, 4, 5};
        String[] gender = {"male", "female", "male", "male", "female"};
        int[] age = {20, 25, 30, 35, 40};

        // 创建两个 Bitmap Index，分别对应 gender 和 age 列
        BitSet genderMale = new BitSet(id.length);
        BitSet genderFemale = new BitSet(id.length);
        BitSet ageLessThan30 = new BitSet(id.length); // 年龄小于 30 的索引
        BitSet ageGreaterThan30 = new BitSet(id.length); // 年龄大于等于 30 的索引

        // 构建 bitmap index
        for (int i = 0; i < id.length; i++) {
            if (gender[i].equals("male")) {
                genderMale.set(i);
            } else {
                genderFemale.set(i);
            }

            if (age[i] < 30) {
                ageLessThan30.set(i);
            } else {
                ageGreaterThan30.set(i);
            }
        }

        // 条件查询：查找年龄小于 30 岁且性别为男性的记录
        BitSet result = new BitSet(id.length);
        result.or(ageLessThan30);
        result.and(genderMale);

        // 输出满足条件的记录
        System.out.println("ID\tGender\tAge");
        for (int i = result.nextSetBit(0); i >= 0; i = result.nextSetBit(i + 1)) {
            System.out.println(id[i] + "\t" + gender[i] + "\t" + age[i]);
        }
    }
}
