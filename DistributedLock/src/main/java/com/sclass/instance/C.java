package com.sclass.instance;

/*Testing x of type class com.sclass.instance.A
x instanceof A true
x instanceof B false  //父类不一定是子类的某个类型
A.isInstance(x) true
B.isInstance(x) false
x.getClass() == A.class true
x.getClass() == B.class false
x.getClass().equals(A.class)) true
x.getClass().equals(B.class)) false

Testing x of type class com.sclass.instance.B
x instanceof A true
x instanceof B true
A.isInstance(x) true
B.isInstance(x) true
x.getClass() == A.class false
x.getClass() == B.class true
x.getClass().equals(A.class)) false
x.getClass().equals(B.class)) true*/

class A {
}

class B extends A {
}

public class C {
	static void print(String a) {
		System.out.println(a);
	}

	static void test(Object x) {
		print("Testing x of type " + x.getClass());
		print("x instanceof A " + (x instanceof A));
		print("x instanceof B " + (x instanceof B));
		print("A.isInstance(x) " + A.class.isInstance(x));
		print("B.isInstance(x) " + B.class.isInstance(x));
		print("x.getClass() == A.class " + (x.getClass() == A.class));
		print("x.getClass() == B.class " + (x.getClass() == B.class));
		print("x.getClass().equals(A.class)) " + (x.getClass().equals(A.class)));
		print("x.getClass().equals(B.class)) " + (x.getClass().equals(B.class)));
	}

	public static void main(String[] args) {
		test(new A());
		test(new B());
	}
}