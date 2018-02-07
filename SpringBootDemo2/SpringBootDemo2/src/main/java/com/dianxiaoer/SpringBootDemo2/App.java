package com.dianxiaoer.SpringBootDemo2;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Hello world!
 *
 */
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class App 
{
    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);

        System.out.println( "Hello World!" );
    }
}
