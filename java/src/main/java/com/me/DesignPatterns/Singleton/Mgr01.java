package com.me.DesignPatterns.Singleton;

/**
 * 懒汉式单例模式
 * 类加载到内存后就实例化一个单例，JVM保证线程安全
 * 简单实用，线程安全。可以推荐使用！
 * 唯一缺点是不管用到与否，在类加载的时候都会进行实例化。
 * Class.forName("")
 * (话说你不用的，你装载它干啥)
 */
public class Mgr01 {
	// static 由JVM初始化的，只有初始化完成之后才可以使用
	private static final Mgr01 INSTANCE = new Mgr01();

	private Mgr01() {}

	public static Mgr01 getInstance(){
		return INSTANCE;
	}

	public void m(){
		System.out.println("m");
	}

	public static void main(String[] args) {
		Mgr01 m1 = Mgr01.getInstance();
		Mgr01 m2 = Mgr01.getInstance();
		System.out.println(m1 == m2);
	}
}
