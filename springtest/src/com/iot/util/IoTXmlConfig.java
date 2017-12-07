package com.iot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class IoTXmlConfig {
	private static Logger logger = LoggerFactory.getLogger(IoTProperties.class);
	private static String defaultPath = IoTDefaultPath.defaultPath;
	private XStream xStream;

	public IoTXmlConfig() {
		super();
		xStream = new XStream(new DomDriver());
	}

	public Object loadConfig(Class clazz, String fileName) throws IOException {
		InputStream inputStream = null;
		Object object = null;
		String newFileName;
		
		newFileName = IoTDefaultPath.getFullPathFilename(fileName);

		if (xStream == null) {
			return object;
		}

		try {
			if (StringUtils.isNotEmpty(defaultPath)) {
				File file = new File(newFileName);
				if (file.exists()) {
					// if file exists, then use global config file.
					inputStream = new FileInputStream(newFileName);
					logger.info("load xml file: " + newFileName);
				} else {
					//inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
					inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
					logger.info("load xml file: " + fileName);
				}
			} else {
				inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
			}

			object = xStream.fromXML(inputStream);
		} catch (IOException e) {
			logger.info("load XML file: " + fileName + "does not exist.");
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return object;
	}

	public void addAlias(String alias, Class clazz) {
		if (xStream != null) {
			xStream.alias(alias, clazz);
		}
	}

	// xml转java对象
	public Object xmlToBean(String xml) {
		return xStream.fromXML(xml);
	}

	// java对象转xml
	public String beanToXml(Object obj) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(obj);
	}
}
