package com.eva.me.junit;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.eva.me.cm.ConfigUtil;

public class JUnitTestCase {

	@Before
	public void setUp() throws Exception {
//		ConfigUtil.getInstance().loadConfigFile();// we don't need use it anymore
//		System.out.println("Config File Load... Init time...");
	}

	@Test
	public void testSetProperty1() {
		System.out.println("Begin set property...<1>");
		
		/*
		 * set properties
		 */
		ConfigUtil.getInstance().setProperty("key0", "value00");
		ConfigUtil.getInstance().setProperty("key1", "value1");
		ConfigUtil.getInstance().setProperty("key2", "value2");
		
		System.out.println("End set property...<1>");
		
		showAllItems();
	}
	
	@Test
	public void testSetProperty2() {
		System.out.println("");

		ConfigUtil.getInstance().setProperty("key0", "value3");//will overwrite value of key 'key0'
		ConfigUtil.getInstance().setProperty("key3", "哈哈哈");
		ConfigUtil.getInstance().setProperty("你好", "value3");
		
		showAllItems();
		
		System.out.println("\n\n"+ConfigUtil.getInstance().getProperty("你好"));
		System.out.println("\n\n"+ConfigUtil.getInstance().getProperty("key3"));
		
	}
	
	@Test
	public void testSetProperty3() {
		System.out.println("change property file ...change config files");
		ConfigUtil.changeConfigFilePath("config2.properties");//have another file but will not be able to open by the editor// can be open
//		ConfigUtil.getInstance().loadConfigFile();
		ConfigUtil.getInstance().setProperty("key0", "value00");
		ConfigUtil.getInstance().setProperty("key1", "value11");
		ConfigUtil.getInstance().setProperty("key2", "value22");
		ConfigUtil.getInstance().setProperty("key0", "value33");//will overwrite value of key 'key0'
		
		showAllItems();
	}

	/**
	 * show all the items  in all setting profiles...
	 * 
	 */
	public void showAllItems() {
		System.out.println("==============show all tests================");
		/*
		 * show all properties
		 */
		Map<String, String> maps= ConfigUtil.getInstance().showConfigFileContent();
		Iterator iterator = maps.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println("key = "+key+" value = "+value);
		}
		System.out.println("==============show all tests ends======================");
	}
	
}
