package com.designmode.singleton;

/**
 * Effective Java 第一版推荐写法
 **/
public class Singleton {
	private static class SingletonHolder {
		private static final Singleton INSTANCE = new Singleton();
	}

	private Singleton() {
	}

	public static final Singleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public void Test() {
		System.out.println("singleton:test");
	}
}