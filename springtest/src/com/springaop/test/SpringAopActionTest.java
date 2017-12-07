package com.springaop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.aspecttest.IUserServ;
import com.aspecttest.User;

public class SpringAopActionTest {
	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("springaop.xml");
		IUserServ iuserServ = (IUserServ) ac.getBean("userServImpl");

		iuserServ.deleteUserById(new User());
		iuserServ.findAllUser();
		iuserServ.saveUser(new User());
	}
}
