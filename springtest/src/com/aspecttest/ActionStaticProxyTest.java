package com.aspecttest;

public class ActionStaticProxyTest {
	public static void main(String[] args) {
		// �û����ʴ������---��Ϣ->Ŀ�����
		IUserServ iuserServ = new UserServProxyImpl(new UserServImpl());
		iuserServ.findAllUser();
	}
}
