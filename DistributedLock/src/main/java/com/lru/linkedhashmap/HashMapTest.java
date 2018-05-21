package com.lru.linkedhashmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapTest {
	public static void main(String[] args) {
		testHashMap();
	}
	
	static void testHashMap() {
		//Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("aa", "@sohu.com");
		map.put("bb", "@163.com");
		map.put("cc", "@sina.com");

		System.out.println("普通的遍历方法，通过Map.keySet遍历key和value");// 普通使用，二次取值
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
		}

		System.out.println("\r\n通过Map.entrySet使用iterator遍历key和value");

		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}

		System.out.println("\r\n通过Map.entrySet遍历key和value"); // 推荐这种，特别是容量大的时候

		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}

		System.out.println("\r\n通过Map.values()遍历所有的value，但不能遍历key");
		for (String v : map.values()) {
			System.out.println("value = " + v);
		}
		
		Iterator<String> it2 = map.keySet().iterator();
		while (it2.hasNext()) {
			String entry = it2.next();
			System.out.println("key= " + entry);
		}
	}
}
