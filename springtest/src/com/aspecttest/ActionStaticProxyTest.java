package com.aspecttest;

public class ActionStaticProxyTest {
	public static void main(String[] args) {
		// 用户访问代理对象---信息->目标对象
		IUserServ iuserServ = new UserServProxyImpl(new UserServImpl());
		iuserServ.findAllUser();
	}
}
