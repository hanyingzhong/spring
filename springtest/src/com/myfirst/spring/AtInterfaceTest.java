package com.myfirst.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.Resource;  
  
class Student {  
    void say() {  
        System.out.println("hello");  
    }  
}  
  
class Person {  
    @Resource(name="student")  
    private Student student;  
  
    //Access can be package-private  
    //所以方法的 public就不要啦  
    void say(){  
        this.student.say();  
    }  
}  
/** 
 * Created by lxk on 2016/9/29 
 */  
public class AtInterfaceTest {  
    public static void main(String[] args) {  
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("file:E:/xxx/intellij_work/TrunkNew/sss.xml");  
        ApplicationContext ctx = new FileSystemXmlApplicationContext("sss.xml");  
        Person p = (Person) ctx.getBean("person");  
        p.say();  
    }  
}
