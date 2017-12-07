package com.aspecttest;

import java.util.List;

public class UserServImpl implements IUserServ {

	@Override
	public int deleteUserById(User user) {
		System.out.println("******执行删除方法******");
		return 0;
	}

	@Override
	public List<User> findAllUser() {
		System.out.println("*******执行查询方法*******");
		return null;
	}

	@Override
	public int saveUser(User user) {
		System.out.println("*******执行添加方法********");
		return 0;
	}

}
