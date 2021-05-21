package com.me.builder;

// TODO 链式调用：用的时候可以...
public class App {
    public static void main(String[] args) {
        //
        Purchase p = new Purchase
                .Builder("S0001") // 获取的是内部类
                .menuId("11")
                .menuName("宫保")
                .build();
        System.out.println(p.menuName);


        LombokPurchase lombokPurchase = LombokPurchase
                .builder()
                .menuId("1")
                .menuName("zs")
                .price(100.0)
                .shipNo("11")
                .build();
        System.out.printf(lombokPurchase.toString());
    }
}
