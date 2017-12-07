package com.myfirst.spring2;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service("initClassTest3")
class InitClassTest3 {
	String test = "aaaaa3";

	@PostConstruct
	public void init() {
		System.out.println(test);
	}
}

@Service("initClassTest")
public class InitClassTest {
	String test = "aaaaa";

	@PostConstruct
	public void init() {
		System.out.println(test);
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("InitClassTest: preDestroy");
	}
}

@Service("initClassTest2")
class InitClassTest2 {
	String test = "aaaaa2";

	@PostConstruct
	public void init() {
		System.out.println(test);
	}
}
