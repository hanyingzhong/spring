package com.aspecttest;

import java.util.List;

public class UserServImpl implements IUserServ {

	@Override
	public int deleteUserById(User user) {
		System.out.println("******ִ��ɾ������******");
		return 0;
	}

	@Override
	public List<User> findAllUser() {
		System.out.println("*******ִ�в�ѯ����*******");
		return null;
	}

	@Override
	public int saveUser(User user) {
		System.out.println("*******ִ����ӷ���********");
		return 0;
	}

}
