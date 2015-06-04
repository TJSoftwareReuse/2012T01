package com.eva.me.server;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.eva.me.cm.ConfigUtil;
import com.team01.fm.ErrorUtil;
import com.team01.fm.ErrorUtilTest;

import pm.PM;

import src.com.team8.License.License;

public class ServerUtil {
	private static final int LICENSE_MAX_SIZE = 10;
	private static final License license = new License(LICENSE_MAX_SIZE);
//	private static final PM pm = new PM();
	private static final String pmPath = "./PMLog/";
	private static final ErrorUtil eu = new ErrorUtil();
	
	// SERVER STATUS
	public static int receivedNum = 0;
	public static int sendBackNum = 0;
	public static int rejectNum = 0;
	public static int supportNum = 0;
	
	
	private static void log(String str) {
		System.out.println(str);
	}
	
	public static void changeCMDir(String cmFilePath) {
		ConfigUtil.changeConfigFilePath(cmFilePath);
	}
	
	public static String doCMPart(String key) {
		String groupName = ConfigUtil.getInstance().getProperty(key);
		if (groupName == null) {
			log("Member : "+key+" can not find a group name!");
		} else {
			log("Member : "+key+" is in group: "+groupName);
		}
		return groupName;
	}
	
	public static boolean doLiensePart() {
		log("License remain: "+license.getRemain()+" / "+license.getLicense());
		return license.inLicense();
	}
	
	public static void changeFMDir(String fmFilePath) {
		eu.set_error_path(fmFilePath);
	}
	
	public static void doFMPart(int errCode) {
		Integer error_id = new Integer(errCode);
		eu.report_an_error(error_id);
		log("FM module finished!");
	}
	
	public static void startPM() {
//		PM.start();
//		PM.setPath(pmPath);
	}
	
	public static void doPMPart(String key, int value) {
//		PM.addItem(key, value);
	}
	
	public static void stopPM() {
//		PM.stop();
	}
	
	@Deprecated
	public static String processAll(String request) {
		String groupName = null;
		doPMPart("Received", ++receivedNum);//received msg
		
		//license valid?
		boolean canGiveService  = doLiensePart();
		if (canGiveService) {
			doPMPart("Support", ++supportNum);
			groupName = doCMPart(request);
		}else {
			doPMPart("Reject", ++rejectNum);
			
		}
		
		return groupName;
	}
}
