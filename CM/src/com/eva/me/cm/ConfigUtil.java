package com.eva.me.cm;

public class ConfigUtil implements Inquiry{
	private String configFilePath ="config.properties";
	
	public boolean readConfigFile() {
		return true;
	}
	
	public void setConfigFilePath(String filePath) {
		this.configFilePath = filePath;
	}

	@Override
	public String getInfo(String keyword) {
		
		return null;
	}
	
	
	
}
