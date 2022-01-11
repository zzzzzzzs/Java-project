package com.me.DesignPatterns.Singleton;

public class App {
    public static void main(String[] args) {
        String s1 = new String();
        String s2 = new String();
        System.out.println(s1 == s2); // fasle 不是单例

        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();
        System.out.println(hungrySingleton1 == hungrySingleton2); // true 单例

        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();

        System.out.println(lazySingleton1 == lazySingleton2); // true 单例
    }
}
