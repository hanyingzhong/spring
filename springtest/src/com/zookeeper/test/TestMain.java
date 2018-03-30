package com.zookeeper.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class TestMain {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {  
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("file:E:/xxx/intellij_work/TrunkNew/sss.xml");  
        ApplicationContext ctx = new FileSystemXmlApplicationContext("root-context.xml");  
 
		for (;;) {
			UserinfoBean userinfoBean = (UserinfoBean) ctx
					.getBean("userinfoBean");
			IdinfoBean idinfoBean = (IdinfoBean) ctx.getBean("idinfoBean");
			logger.info("id-->" + idinfoBean.getId() + "\tusername-->"
					+ userinfoBean.getUsername() + "\t" + "password-->"
					+ userinfoBean.getPassword());
			Thread.sleep(10000);
		}
        
    }  
}
