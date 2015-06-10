package com.eva.me.server;

import pm.PM;
import src.com.team8.License.License;

import com.eva.me.cm.ConfigUtil;
import com.team01.fm.ErrorUtil;

public class ServerUtil {
	private static final int LICENSE_MAX_SIZE = 10;
	private static final License license = new License(LICENSE_MAX_SIZE);
	private static final PM pm = new PM();
	private static String pmPath = "PMLog";
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
	
	/**
	 * Input group name, get group member info
	 * @param groupName
	 * @return
	 */
	public static String inquireGroupInfo(String groupName) {
		changeCMDir("group.properties");
		return doCMPart(groupName);
	}
	
	/**
	 * Input member name, get member's group name
	 * @param memName
	 * @return
	 */
	public static String inquireMemberName(String memName) {
		changeCMDir("member.properties");
		return doCMPart(memName);
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
	
	public static void changePMDir(String pmFilePath) {
		pm.setPath(pmFilePath);
	}
	
	public static void startPM() {
		pm.setPath(pmPath);
		pm.start();
	}
	
	public static void doPMPart(String key, int value) {
		pm.addItem(key, value);
	}
	
	public static void stopPM() {
		pm.stop();
	}
	
	public static void changePMIntv(long interval) {
		pm.resetInterval(interval);
	}
	
	public static void loadAllNeedInfomation() {
		//Load FM Dir
		String fmPath = ConfigUtil.getInstance().getProperty("FMPath");
		if (fmPath == null) {
			fmPath = "FMLog";//default value
		}
		ServerUtil.changeFMDir(fmPath);
		
		//Load PM Dir
		pmPath = ConfigUtil.getInstance().getProperty("PMPath");
		if (pmPath == null) {
			pmPath = "PMLog";
		}
		ServerUtil.changePMDir(pmPath);
		
		//Load CM Dir
		String cmPath = ConfigUtil.getInstance().getProperty("CMPath");
		if (cmPath == null) {
			cmPath = "config.properties";
		}
		ServerUtil.changeCMDir(cmPath);
		
		//Load PM Interval
		long intv = 1000;//1s
		String pmIntv = ConfigUtil.getInstance().getProperty("PMIntv");
		if (pmIntv != null) {
			try {
				intv = Long.parseLong(pmIntv);
			} catch (Exception e) {
				e.printStackTrace();
				intv=1000;
			}
		}
		ServerUtil.changePMIntv(intv);
		
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
