package com.enumtest;

import java.util.EnumSet;

enum Type {
	TYPE_ONE, TYPE_TWO, TYPE_THREE, TYPE_FOUR
}

public class EnumSetDemo2 {
	public static void main(String[] args) {
		EnumSet<Type> set = EnumSet.of(Type.TYPE_ONE, Type.TYPE_FOUR);
		
		System.out.println(set);
	}
}
