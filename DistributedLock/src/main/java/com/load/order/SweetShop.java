package com.load.order;

class Candy {
	static {
		System.out.println("Loading Candy");
	}
}

class Gum {
	static {
		System.out.println("Loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("Loading Cookie");
	}
}

public class SweetShop {
	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		print("inside main");
		new Candy();
		print("After creating Candy");
		try {
			Class.forName("com.load.order.Gum");
		} catch (ClassNotFoundException e) {
			print("Couldn't find Gum");
		}
		print("After Class.forName(\"com.load.order.Gum\")");
		new Cookie();
		print("After creating Cookie");
	}
}