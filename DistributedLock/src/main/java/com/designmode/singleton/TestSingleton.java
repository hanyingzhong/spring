package com.designmode.singleton;

public class TestSingleton {
	public static void main(String[] args) {
		Singleton instance = Singleton.getInstance();

		instance.Test();

		Singleton2.INSTANCE.test();
		Singleton2.TWO.test();
	}
}
