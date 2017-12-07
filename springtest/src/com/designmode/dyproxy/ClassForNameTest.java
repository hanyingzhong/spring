package com.designmode.dyproxy;

import javax.annotation.PostConstruct;

public class ClassForNameTest {
	static final String className = "com.designmode.dyproxy.ClassForNameTest";

	@PostConstruct
	private void init(){
		System.out.println("start....");
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(ClassForNameTest.class);// ͨ����.class���Class����

		ClassForNameTest a = new ClassForNameTest();
		System.out.println(a.getClass());// ͨ�� ʵ����.getClass()���Class����
		System.out.println(Class.forName(className));// ͨ��Class.forName(ȫ·��)���Class����
		System.out.println(".................................");
		System.out.println(a);// ʹ�ò�ͬ�ķ�ʽ��������
		System.out.println(ClassForNameTest.class.newInstance());
		System.out.println(a.getClass().newInstance());
		System.out.println(Class.forName(className).newInstance());
	}
}
