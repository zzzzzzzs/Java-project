package com.me.DesignPatterns.Singleton;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 懒汉式单例  将 synchronized 加到需要的代码块上 通过双重检查机制来实现单例 能实现，但是存在可见性问题
 */
public class Mgr06 {

	private static volatile Mgr06 INSTANCE; // JIT 如果不加 volatile 会出现指令重排序

	private Mgr06() {}

	public static Mgr06 getInstance() {
		// 业务逻辑代码省略
		if(INSTANCE == null) {
			synchronized (Mgr06.class) {
				//双重检查 第一次判断不能省去 可以拦截大部分无用调用
				if(INSTANCE == null) {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					/*
					 * new 一个对象JVM分为三步
					 * 1、对象申请内存 （有默认值）
					 * 2、成员变量初始化 （初始值）
					 * 3、将内存地址赋值给 INSTANCE（栈中的变量）
					 * 如果指令重排序，有可能第三步和第二部换了位置。（超高并发才能出现）
					 * 看字节码（new不是一条指令，是3条以上指令）
						0 new #2 <java/lang/Object>
						3 dup
						4 invokespecial #1 <java/lang/Object.<init>>
						7 astore_1
						8 return
					 **/
					INSTANCE = new Mgr06();
				}
			}
		}
		return INSTANCE;
	}

	public static void m(){
		System.out.println("m");
	}

	public static void main(String[] args) {
		IntStream.range(0, 100).forEach((i) -> {
			new Thread(() -> System.out.println(Mgr06.getInstance().hashCode())).start();
		});
	}
}
