package com.me.Collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author zs
 * @date 2021/9/23.
交并补
 */
public class CollectionTest {

    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    @Before
    public void initFunc(){
        list1.add(1);
        list1.add(2);
        list2.add(1);
        list2.add(3);
    }


    // 交集：求List1和List2中都有的元素。
    @Test
    public void intersection1() {
        list1.retainAll(list2);
        System.out.println(list1);
    }

    // 求并集(不去重)---将一个集合全部加入另一个集合
    @Test
    public void union1() {
        list1.addAll(list2);
        System.out.println(list1);
    }

    // 求并集(去重)
    @Test
    public void union2() {
        list1.removeAll(list2);
        list1.addAll(list2);
        System.out.println(list1);
    }
    // 差（补）集 求List1中有的但是List2中没有的元素:
    @Test
    public void diffSet() {
        list1.removeAll(list2);
        System.out.println(list1);
    }
}
