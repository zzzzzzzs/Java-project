package com.me.jvm.classloader;

/**
 * @author zs
 * @date 2022/1/11
 * 热部署小例子，这个例子不能完成热部署
 * load 一次之后，都回去已经加载的类中找，如果找到了不会重新加载，所以后面再 load 多少次都没有什么用
 */
public class T011_ClassReloading1 {
    public static void main(String[] args) throws Exception {
        T006_MSBClassLoader msbClassLoader = new T006_MSBClassLoader();
        Class clazz = msbClassLoader.loadClass("com.me.jvm.Hello");

        msbClassLoader = null;
        System.out.println(clazz.hashCode());

        msbClassLoader = null;

        msbClassLoader = new T006_MSBClassLoader();
        Class clazz1 = msbClassLoader.loadClass("com.me.jvm.Hello");
        System.out.println(clazz1.hashCode());

        System.out.println(clazz == clazz1); // true
    }
}
