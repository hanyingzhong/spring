package com.aspect.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AspectJTest {
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aoptest.xml");
		ApplicationContext context = new FileSystemXmlApplicationContext("aoptest.xml");
		// 代理为指向Interface的代理
        Performer drama = (Performer)context.getBean("drama");
        drama.perform();
	}
}
