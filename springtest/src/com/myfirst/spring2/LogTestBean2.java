package com.myfirst.spring2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.*;
import org.springframework.stereotype.Service;


//if the bean defined in xml, then @Service is not necessary
//@Service
public class LogTestBean2 {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(LogTestBean.class);
    //private static Logger logger = Logger.getLogger(LogTestBean2.class);
	
	public void initMethod(){
		//logger.setLevel(Level.ALL);
		System.out.println("dddddddddddddddd");
	}

	public void destroyMethod(){
		System.out.println("eeeeeeeeeeeeeeeeeee");
	}
	
	public static void show(){
		logger.debug("init start...test...");
		logger.info("init start...test...");
		logger.error("init start...test...");
	}	
}
