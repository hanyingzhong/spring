package com.iot.util;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class IoTTestXmlConfig {
	static IoTXmlConfig xmlConfig = null;
	static Person person;

	public static void main(String[] args) {
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("file:E:/xxx/intellij_work/TrunkNew/sss.xml");
		ApplicationContext ctx = new FileSystemXmlApplicationContext("iottest.xml");

		xmlConfig = new IoTXmlConfig();
		xmlConfig.addAlias("person", Person.class);
		xmlConfig.addAlias("address", Address.class);

		try {
			person = (Person) xmlConfig.loadConfig(IoTXmlConfig.class, "person.xml");
		} catch (IOException e) {

		} finally {
			System.out.println(person.toString());
		}
	}
}
