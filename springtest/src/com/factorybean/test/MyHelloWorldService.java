package com.factorybean.test;

import org.springframework.stereotype.Component;

@Component
public class MyHelloWorldService implements HelloWorldService{

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}
}
