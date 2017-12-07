package com.aspecttest;

public class AopLogActionTest {
	public static void main(String[] args) {
		// �����������iuserServ
		AopLogHandler handler = new AopLogHandler();
		IUserServ iuserServ = (IUserServ) handler.createProxy(new UserServImpl());
		iuserServ.deleteUserById(new User());
		iuserServ.saveUser(new User());
	}
}
