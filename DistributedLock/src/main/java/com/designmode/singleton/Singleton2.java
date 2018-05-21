package com.designmode.singleton;

/**
 * Effective Java 第二版推荐写法
 **/
public enum Singleton2 {
	INSTANCE, TWO;
	public void test() {
		System.out.println(getClass().getName() + ":test");
	}
}
