package com.load.order;

/*static A
static B
-------main start-------
I'm A class
HelloA
I'm B class
HelloB
I'm A class
HelloA
I'm B class
HelloB
-------main end-------*/


class HelloA {

	public HelloA() {
		System.out.println("HelloA");
	}

	{
		System.out.println("I'm A class");
	}

	static {
		System.out.println("static A");
	}

	void test() {

		String s="abc";

		System.out.println("s=" + s);
	}

}

public class HelloB extends HelloA {
	public HelloB() {
		System.out.println("HelloB");
	}

	{
		System.out.println("I'm B class");
	}

	static {
		System.out.println("static B");
	}

	public static void main(String[] args) {
		System.out.println("-------main start-------");
		new HelloB();
		new HelloB();
		System.out.println("-------main end-------");
	}
}
