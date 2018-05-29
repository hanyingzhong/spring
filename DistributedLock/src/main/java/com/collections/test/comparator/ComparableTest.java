package com.collections.test.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableTest {
	public static void main(String[] args) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		list.add(new UserInfo(1, 21, "name1"));
		list.add(new UserInfo(2, 27, "name1"));
		list.add(new UserInfo(3, 15, "name1"));
		list.add(new UserInfo(5, 24, "name1"));
		list.add(new UserInfo(4, 24, "name1"));

		// 对该类排序
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

class UserInfo implements Comparable<UserInfo> {
	private int userid;
	private int age;
	private String name;

	public UserInfo(int userid, int age, String name) {
		this.userid = userid;
		this.age = age;
		this.name = name;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.userid + "," + this.age + "," + this.name;
	}

	@Override
	public int compareTo(UserInfo o) {
		// 如果年龄相同，则比较userid，也可以直接 return this.age-o.age;
		if (this.age - o.age == 0) {
			return this.userid - o.userid;
		} else {
			return this.age - o.age;
		}
	}

}