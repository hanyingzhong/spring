package com.lru.treemap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
	/**
	 * @author ZJYANG TreeMap的使用
	 */

	public static void main(String[] args) {
		// 初始化自定义比较器
		MyComparator comparator = new MyComparator();
		// 初始化一个map集合
		Map<String, String> map = new TreeMap<String, String>(comparator);
		// 存入数据
		map.put("aaa", "aaa");
		map.put("bbb", "bbb");
		map.put("fff", "fff");
		map.put("mmm", "mmm");
		map.put("ggg", "ggg");
		map.put("sss", "sss");
		// 遍历输出
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(map.get(key));
		}
	}
	
	/////Comparable
	/////Comparator
	
	static class MyComparator implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			String param1 = (String) o1;
			String param2 = (String) o2;
			return -param1.compareTo(param2);
		}
	}

}
