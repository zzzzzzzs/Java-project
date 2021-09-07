package com.me.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zs
 * @date 2021/9/3.

 */
public class ListUtils {
    // 返回不同类型返回值，由于泛型擦除的原因可以填写不同的类型
    public static List diffValue(){
        ArrayList oo = new ArrayList();
        oo.add(1111);
        oo.add("aaaa");
        return oo;
    }

    public static void main(String[] args) {
        List list = diffValue();
        System.out.println(list.get(0).getClass());
        System.out.println(list.get(1).getClass());
    }

}
