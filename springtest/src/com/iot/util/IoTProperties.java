package com.iot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service
class IoTDefaultPath{
	static String defaultPath="/etc/phicomm";

	IoTDefaultPath(String path){
		defaultPath = path;
	}
		
	public static String getDefaultPath(){
		return defaultPath;
	}

	public static String getFullPathFilename(String configFilename){
		return defaultPath + File.separator + configFilename;
	}

}

public class IoTProperties{
	private static Logger logger = LoggerFactory.getLogger(IoTProperties.class);	
	private static String defaultPath = IoTDefaultPath.getDefaultPath();
	
	static{
		defaultPath = IoTDefaultPath.defaultPath;
	}

	public static Properties loadProperties(Class<?> clazz, String propertiesFile) throws IOException {
		Properties  properties  = null;
		InputStream inputStream = null;
		String      fileName    = null;

		try {
			properties = new Properties();
			if (StringUtils.isNotEmpty(defaultPath)) {
				//fileName = defaultPath + File.separator + propertiesFile;
				fileName = IoTDefaultPath.getFullPathFilename(propertiesFile);
				File file = new File(fileName);
				if (file.exists()) {
					//if file exists, then use global config file.
					inputStream = new FileInputStream(fileName);
					logger.info("load property file: " + fileName);
				} else {
					inputStream = clazz.getClassLoader().getResourceAsStream(propertiesFile);
					logger.info("load property file: " + propertiesFile);
				}
			} else {
				inputStream = clazz.getClassLoader().getResourceAsStream(propertiesFile);
			}
			properties.load(inputStream);			
		} catch (IOException e) {
			logger.info("load property file: " + fileName + "does not exist.");
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return properties;
	}
}
