package com.eva.me.cm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil{
	private static ConfigUtil instance = null;
	private static String  configFilePath = "config.properties";
	private Properties configProperties = null;
	private String charSet = "utf-8";
	
	public static ConfigUtil getInstance() {
		if (instance == null) {
			instance = new ConfigUtil();
			instance.loadConfigFile();
		}
		return instance;
	}
	
	/**
	 * default configure file name: 'config.properties', if not specified using this method. 
	 * 
	 * @param filePath  Another Configure File's Directory
	 */
	public static void changeConfigFilePath(String filePath) {
		configFilePath = filePath;
		getInstance().loadConfigFile();
	}
	
	/**
	 * Load Configure Files with exception catch <br>
	 * Note: <br>
	 * We really recommend you to call this method before you reading or changing
	 * every config file, but if you don't do like this, it doesn't matter, 
	 *  it just will ensure the config file is correct.	
	 */
	private void loadConfigFile() {
		configProperties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(configFilePath);
			configProperties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Config File: "+configFilePath+" Not Found,\n Generating new config file...");
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
	
	/**
	 * get properties
	 * @param key
	 * @return return null if can not find param 'key'
	 */
	public String getProperty(String key) {
		String value = null;
		if (configProperties == null) {
			loadConfigFile();//initialize... if is null, then load....
		}
		value = configProperties.getProperty(key);
		return value;
	}
	
	/**
	 * Set Properties with key value;<br>
	 * 		&nbsp if 'key' not exists, will create a 'key, value' pair in the configure file; <br>
	 * 		&nbsp if 'key' exists, will use the new 'value' to override the origin value of 'key'<br>
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, String value) {
		if (configProperties == null) {
			configProperties = new Properties();
		}
		try {
			OutputStream outputStream = new FileOutputStream(configFilePath);
			configProperties.setProperty(key, value);
			configProperties.store(outputStream, null);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: store failed...");
			e.printStackTrace();
		}
	}
}
