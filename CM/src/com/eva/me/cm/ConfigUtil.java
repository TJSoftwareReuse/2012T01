package com.eva.me.cm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil{
	private static ConfigUtil instance = null;
	private String configFilePath ="config.properties";
	private Properties configProperties = null;
	
	public static ConfigUtil getInstance() {
		if (instance == null) {
			instance = new ConfigUtil();
		}
		return instance;
	}
	
	/**
	 * default configure file name: 'config.properties'
	 * 
	 * @param filePath  Another Configure File's Directory
	 */
	private void changeConfigFilePath(String filePath) {
		this.configFilePath = filePath;
	}
	
	/**
	 * Load Configure Files with exception catch
	 */
	public void loadConfigFile() {
		configProperties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(configFilePath);
			configProperties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Config File: "+configFilePath+" Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Load Failure..");
			e.printStackTrace();
		}
	}
	
	/**
	 * Reveal All Properties In Configure Files
	 * @return Map<String, String> results
	 */
	public Map<String, String> showConfigFileContent() {
		if (configProperties == null) {
			loadConfigFile();//initialize... if is null, then load....
		}
		Map<String,String> results = new HashMap<String,String>();
		Enumeration<?> enumeration = configProperties.propertyNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			String value = configProperties.getProperty(key);
			results.put(key, value);
		}
		return results;
	}
	
}
