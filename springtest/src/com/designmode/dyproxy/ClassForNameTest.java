package com.designmode.dyproxy;

import javax.annotation.PostConstruct;

public class ClassForNameTest {
	static final String className = "com.designmode.dyproxy.ClassForNameTest";

	@PostConstruct
	private void init(){
		System.out.println("start....");
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(ClassForNameTest.class);// 通过类.class获得Class对象

		ClassForNameTest a = new ClassForNameTest();
		System.out.println(a.getClass());// 通过 实例名.getClass()获得Class对象
		System.out.println(Class.forName(className));// 通过Class.forName(全路径)获得Class对象
		System.out.println(".................................");
		System.out.println(a);// 使用不同的方式创建对象
		System.out.println(ClassForNameTest.class.newInstance());
		System.out.println(a.getClass().newInstance());
		System.out.println(Class.forName(className).newInstance());
	}
}
