package com.iot.util;

import java.util.List;

public class Person {
	// ����
	private String name;
	// �Ա�
	private String sex;
	// ����
	private int age;
	// ��ַ
	private List<Address> Address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Address> getAddress() {
		return Address;
	}

	public void setAddress(List<Address> address) {
		Address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + ", Address=" + Address + "]";
	}	
}
