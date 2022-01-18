package com.me.DesignPatterns.Singleton;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 懒汉式单例  将synchronized加到需要的代码块上 这样不能解决问题
 */
public class Mgr05 {

	private static Mgr05 INSTANCE;

	private Mgr05() {}

	public static  Mgr05 getInstance() {
		if(INSTANCE == null) {
			// 妄图通过减少同步代码块的方式提高效率，然后不可行（锁的细化）
			synchronized (Mgr05.class) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				INSTANCE = new Mgr05();
			}
		}
		return INSTANCE;
	}

	public static void m(){
		System.out.println("m");
	}

	public static void main(String[] args) {
		IntStream.range(0, 100).forEach((i) -> {
			new Thread(() -> System.out.println(Mgr05.getInstance().hashCode())).start();
		});
	}
}
