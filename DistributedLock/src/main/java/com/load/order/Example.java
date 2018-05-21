package com.load.order;

public class Example {

	String str = new String("good");

	char[] ch = { 'a', 'b', 'c' };

	public static void main(String args[]) {

		Example ex = new Example();

		ex.change(ex.str, ex.ch);

		System.out.print(ex.str + " and ");

		System.out.print(ex.ch);

		int[] a = { 0, 1, 4 };
		ex.changeInt(a);
		System.out.println(" " + a[0]);
	}

	public void change(String str, char ch[]) {

		str = "test ok";

		ch[0] = 'g';

	}

	public void changeInt(int[] aa) {
		aa[0] = 100;
	}
}
