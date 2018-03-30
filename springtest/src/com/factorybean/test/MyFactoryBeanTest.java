package com.factorybean.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zookeeper.test.Main;

public class MyFactoryBeanTest {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("factory-bean.xml");

		HelloWorldService helloWorldService = (HelloWorldService) ctx.getBean("fbHelloWorldService");
		//helloWorldService.getBeanName();
		helloWorldService.sayHello();
	}
}
