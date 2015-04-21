package com.eva.me.cm;

import java.util.Properties;

public class ConfigUtil{
	private ConfigUtil instance = null;
	private String configFilePath ="config.properties";
	private Properties configProperties = null;
	
	public ConfigUtil getInstance() {
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
	public void changeConfigFilePath(String filePath) {
		this.configFilePath = filePath;
	}
	
	
	
}
