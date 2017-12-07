package com.iot.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class IoTTest {
    public static void main(String[] args) {  
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("file:E:/xxx/intellij_work/TrunkNew/sss.xml");  
        ApplicationContext ctx = new FileSystemXmlApplicationContext("iottest.xml");  
        
        try{
        	Properties prop1 = IoTProperties.loadProperties(IoTTest.class, "log4j.properties");
        	//Properties prop2 = IoTProperties.loadProperties(IoTTest.class, "log4j2.properties");
        	
        }
        catch(IOException e)
        {
        	
        }
        
        System.out.println(System.getProperty("sun.boot.class.path"));
    }  
}
