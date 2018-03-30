package com.aspect.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	// 定义切点
	@Pointcut("execution(* com.aspect.annotation.Performer.perform(..))")
	public void perform() {
	}

	@Before("perform()")
	public void takeSeas() {
		System.out.println("The audience is taking their seats.");
	}

	@Before("perform()")
	public void turnOffPhone() {
		System.out.println("The audience is turn off their cellphone.");
	}

	@AfterReturning("perform()")
	public void applaund() {
		System.out.println("CLAP CLAP CLAP CLAP ...");
	}

	@AfterThrowing("perform()")
	public void demandRefund() {
		System.out.println("Boo! we want our money back!");
	}

	@Around("perform()")
	public void watchPerfomance(ProceedingJoinPoint joinPoint) {

		try {
			Long start = System.currentTimeMillis();

			joinPoint.proceed();

			long end = System.currentTimeMillis();

			System.out.println("The performance took " + (end - start) + " milliseconds");

		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

	}
}
