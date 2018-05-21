package com.enumtest;

public class EnumSetDemoBitOp {
	// 定义位域变量
	public static final int TYPE_ONE = 1 << 0; // 1
	public static final int TYPE_TWO = 1 << 1; // 2
	public static final int TYPE_THREE = 1 << 2; // 4
	public static final int TYPE_FOUR = 1 << 3; // 8

	public static void main(String[] args) {
		// 位域运算
		int type = TYPE_ONE | TYPE_TWO | TYPE_THREE | TYPE_FOUR;
		
		System.out.println(type);
	}
}
