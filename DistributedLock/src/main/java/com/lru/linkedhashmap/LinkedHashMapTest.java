package com.lru.linkedhashmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapTest {
	static void testLinkedHash() {
		// 默认情况下LinkedHashMap的遍历模式是插入模式，如果想显式地指定为get读取模式，那么要将
		// 其构造方法的参数置为true，（false 表示的是插入模式）
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(16, (float) 0.75, false);

		linkedHashMap.put("name", "louis");
		linkedHashMap.put("age", "24");
		linkedHashMap.put("sex", "male");
		linkedHashMap.get("name");//get()方法调用，不会导致对应的entry移动到双向链表的最后位置
		
		Iterator<Entry<String, String>> iterator = linkedHashMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("------------------");
	}

	static void getSequeceLinkedHashMap() {
		// 默认情况下LinkedHashMap的遍历模式是插入模式，如果想显式地指定为get读取模式，那么要将
		// 其构造方法的参数置为true，（false 表示的是插入模式）
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(16, (float) 0.75, true);

		linkedHashMap.put("name", "louis");
		linkedHashMap.put("age", "24");
		linkedHashMap.put("sex", "male");
		linkedHashMap.get("name");// get()方法调用，导致对应的entry移动到双向链表的最后位置
		linkedHashMap.get("sex"); //accessOrder the ordering mode - true for access-order, false for insertion-order
		linkedHashMap.get("name");// get()方法调用，导致对应的entry移动到双向链表的最后位置
		linkedHashMap.get("name");// get()方法调用，导致对应的entry移动到双向链表的最后位置

		///如果某个entry频繁读取后，该entry会存放在双向链表的末尾。也就是
		///很少被访问的entry会存放在双向链表的表头
		///Least recently usage
		
		Iterator<Entry<String, String>> iterator = linkedHashMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static void main(String[] args) {
		testLinkedHash();
		
		getSequeceLinkedHashMap();
	}
}
