package com.eva.me.server;

import src.com.team8.License.License;

public class ServerUtil {
	private static final int LICENSE_MAX_SIZE = 10;
	private static final License license = new License(LICENSE_MAX_SIZE);
	
	private static void log(String str) {
		System.out.println(str);
	}
	
	public static void doCMPart() {
		
	}
	
	public static boolean doLiensePart() {
		log("License remain: "+license.getRemain()+" / "+license.getLicense());
		return license.inLicense();
	}
	
	public static void doFMPart() {
		
	}
	
	public static void doPMPart() {
		
	}
	
}
