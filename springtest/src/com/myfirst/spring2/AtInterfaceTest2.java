package com.myfirst.spring2;

import java.util.*;
import java.io.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

class Student {
	String name;
	String name2; 
    void say() {  
        System.out.println("hello:" + name2);  
    }
	public String getName2() {
		return name2;
	}
	//use property to transfer param...
	public void setName2(String name2) {
		this.name2 = name2;
	}  
}  
  
class Person {  
	boolean sex;
	//the name must be same as the beanid define in sss2xml. 
    //(1)@Resource  (name="ss")
	//(2)@Autowired
    //(3)
	@Autowired 	
	@Qualifier(value = "ss")
    private Student student;  

	public Person(boolean sex){
		this.sex = sex;
	}
		
    //Access can be package-private  
    //所以方法的 public就不要啦  
    void say(){  
        this.student.say();  
        System.out.println("sex:" + sex);  
    }  
}  

/** 
 * Created by lxk on 2016/9/29 
 */
public class AtInterfaceTest2 {  
    public static void main(String[] args) {  
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("file:E:/xxx/intellij_work/TrunkNew/sss.xml");  
        ApplicationContext ctx = new FileSystemXmlApplicationContext("sss2.xml");  
        Person p = (Person) ctx.getBean("person");  
        p.say();    
        
        //LogTestBean.show();
        LogTestBean2.show();
        
        String path2 = ClassLoader.getSystemResource("").toString();  
        String path3 = Thread.currentThread().getContextClassLoader ().getResource("").toString();
        System.out.println("path2 = " + path2);   
        System.out.println("path3 = " + path3.replace("file:/", ""));   
        
        testLoadedProperties("log4j.properties");
    }  
    
    public static Properties loadPropertiesFile(String fileName){
        Properties prop = new Properties();
               
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inStream    = classLoader.getResourceAsStream(fileName);
        if(null != inStream){
            try {
                prop.load(inStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }     
    
    public static void testLoadedProperties(String fileName){
    	Properties prop  = loadPropertiesFile("log4j.properties");
    	
    	prop.getProperty("");    		
    }
    
}
