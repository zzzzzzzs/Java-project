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

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    @Before
    public void initFunc(){
        list1.add("fjc306015018-wxid_7gd1lbktg5sl22");
        list1.add("wxid_25u83h9s28ou22-wxid_klvfiaekma9k21");
        list1.add("wxid_25u83h9s28ou22-wxid_pcbimrp1sgci22");
        list1.add("wxid_25u83h9s28ou22-wxid_xw501w9n968o22");
        list1.add("wxid_275odsysygf41-wxid_7gd1lbktg5sl22");
        list1.add("wxid_4kndov2uaxad21-wxid_7gd1lbktg5sl22");
        list1.add("wxid_7gd1lbktg5sl22-wxid_88j3kuop4m5t21");
        list1.add("wxid_7gd1lbktg5sl22-wxid_dx3y6yca93r522");
        list1.add("wxid_7gd1lbktg5sl22-wxid_hmykigknsx5g21");
        list1.add("wxid_7gd1lbktg5sl22-wxid_qjsy43mhe2je22");
        list1.add("wxid_7gd1lbktg5sl22-wxid_xlrpxqn31mr811");
        list1.add("wxid_7gd1lbktg5sl22-yeai72");
        list1.add("wxid_h7opxog826j422-wxid_wf4usdpkdfy712");
        list1.add("wxid_omvd1erqfixe22-wxid_wf4usdpkdfy712");
        list1.add("wxid_u3xsuisi9gxb22-wxid_wf4usdpkdfy712");
        list1.add("wxid_wc7cqg20lsmo12-wxid_wf4usdpkdfy712");

        list2.add("wxid_275odsysygf41-wxid_7gd1lbktg5sl22");
        list2.add("wxid_7gd1lbktg5sl22-wxid_88j3kuop4m5t21");
        list2.add("wxid_u3xsuisi9gxb22-wxid_wf4usdpkdfy712");
        list2.add("wxid_7gd1lbktg5sl22-wxid_dx3y6yca93r522");
        list2.add("wxid_25u83h9s28ou22-wxid_pcbimrp1sgci22");
        list2.add("wxid_wc7cqg20lsmo12-wxid_wf4usdpkdfy712");
        list2.add("wxid_h7opxog826j422-wxid_wf4usdpkdfy712");
        list2.add("wxid_25u83h9s28ou22-wxid_xw501w9n968o22");
        list2.add("wxid_omvd1erqfixe22-wxid_wf4usdpkdfy712");
        list2.add("wxid_7gd1lbktg5sl22-wxid_qjsy43mhe2je22");
        list2.add("wxid_25u83h9s28ou22-wxid_klvfiaekma9k21");


//        list1.add(1);
//        list1.add(2);
//        list2.add(1);
//        list2.add(3);
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
