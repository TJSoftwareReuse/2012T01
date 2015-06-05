/**
 * 
 */
package com.eva.me.server.junittest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.eva.me.cm.ConfigUtil;
import com.eva.me.server.ServerUtil;

/**
 * @author Zing
 *
 */
public class ServerUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@Test
	public void testCMModuleInit() {
		ConfigUtil.getInstance().setProperty("吴逸菲", "第一组");
		ConfigUtil.getInstance().setProperty("黄徐欢", "第一组");
		ConfigUtil.getInstance().setProperty("李亚斯", "第一组");
		ConfigUtil.getInstance().setProperty("许铭淏", "第一组");
		
		ConfigUtil.getInstance().setProperty("王笑盈", "第二组");
		ConfigUtil.getInstance().setProperty("孙琳", "第二组");
		ConfigUtil.getInstance().setProperty("许舰", "第二组");
		ConfigUtil.getInstance().setProperty("李伟", "第二组");
		ConfigUtil.getInstance().setProperty("关晨", "第二组");
		
		ConfigUtil.getInstance().setProperty("胡圣托", "第三组");
		ConfigUtil.getInstance().setProperty("邓冰茜", "第三组");
		ConfigUtil.getInstance().setProperty("张奕格", "第三组");
		ConfigUtil.getInstance().setProperty("郑勇俊", "第三组");
		ConfigUtil.getInstance().setProperty("代蒙", "第三组");

		ConfigUtil.getInstance().setProperty("梁竞文", "第四组");
		ConfigUtil.getInstance().setProperty("彭秋辰", "第四组");
		ConfigUtil.getInstance().setProperty("胡文超", "第四组");
		ConfigUtil.getInstance().setProperty("杨爽", "第四组");

		ConfigUtil.getInstance().setProperty("关清晨", "第五组");
		ConfigUtil.getInstance().setProperty("杨春雨", "第五组");
		ConfigUtil.getInstance().setProperty("周泽宏", "第五组");
		ConfigUtil.getInstance().setProperty("杨宇歆", "第五组");
		ConfigUtil.getInstance().setProperty("张良", "第五组");

		ConfigUtil.getInstance().setProperty("喻帅", "第六组");
		ConfigUtil.getInstance().setProperty("刘蕊", "第六组");
		ConfigUtil.getInstance().setProperty("张旭晨", "第六组");
		ConfigUtil.getInstance().setProperty("韦吾境", "第六组");
		ConfigUtil.getInstance().setProperty("时雨", "第六组");

		ConfigUtil.getInstance().setProperty("尹巧", "第七组");
		ConfigUtil.getInstance().setProperty("方程", "第七组");
		ConfigUtil.getInstance().setProperty("赵鹏", "第七组");
		ConfigUtil.getInstance().setProperty("黄昕", "第七组");
		ConfigUtil.getInstance().setProperty("于航", "第七组");

		ConfigUtil.getInstance().setProperty("丁宇笙", "第八组");
		ConfigUtil.getInstance().setProperty("邱尚昭", "第八组");
		ConfigUtil.getInstance().setProperty("高屹", "第八组");
		ConfigUtil.getInstance().setProperty("杨丰", "第八组");
		ConfigUtil.getInstance().setProperty("贺志鹏", "第八组");
		
		ConfigUtil.getInstance().setProperty("陈璐", "第九组");
		ConfigUtil.getInstance().setProperty("褚振方", "第九组");
		ConfigUtil.getInstance().setProperty("陈启源", "第九组");
		ConfigUtil.getInstance().setProperty("于自跃", "第九组");

		ConfigUtil.getInstance().setProperty("姜木慧", "第十组");
		ConfigUtil.getInstance().setProperty("王远", "第十组");
		ConfigUtil.getInstance().setProperty("刘禹嘉", "第十组");
		ConfigUtil.getInstance().setProperty("叶剑权", "第十组");
		
	}
	
	@Ignore
	@Test
	public void testCMModuleForNewGroup() {
//		ConfigUtil.getInstance().changeConfigFilePath("group_member.properties");
		ConfigUtil.getInstance().setProperty("第一组","吴逸菲,黄徐欢,李亚斯,许铭淏");
		ConfigUtil.getInstance().setProperty("第二组","王笑盈,孙琳,许舰,李伟,关晨");
		ConfigUtil.getInstance().setProperty("第三组","胡圣托,邓冰茜,张奕格,郑勇俊,代蒙");
		ConfigUtil.getInstance().setProperty("第四组","梁竞文,彭秋辰,胡文超,杨爽");
		ConfigUtil.getInstance().setProperty("第五组","关清晨,杨春雨,周泽宏,杨宇歆,张良");
		ConfigUtil.getInstance().setProperty("第六组","喻帅,刘蕊,张旭晨,韦吾境,时雨");
		ConfigUtil.getInstance().setProperty("第七组","尹巧,方程,赵鹏,黄昕,于航");
		ConfigUtil.getInstance().setProperty("第八组","丁宇笙,邱尚昭,高屹,杨丰,贺志鹏");
		ConfigUtil.getInstance().setProperty("第九组","陈璐,褚振方,陈启源,于自跃");
		ConfigUtil.getInstance().setProperty("第十组","姜木慧,王远,刘禹嘉,叶剑权");
	}

	@Ignore
	@Test
	public void testCMModule() {
		assertEquals("Be Same", "第一组", ServerUtil.doCMPart("许铭淏"));
		assertEquals("Be Same", "第七组", ServerUtil.doCMPart("尹巧"));
		assertEquals("Be Same", "第八组", ServerUtil.doCMPart("邱尚昭"));
		assertEquals("Be Same", "第九组", ServerUtil.doCMPart("陈璐"));
		assertEquals("Be Same", "第十组", ServerUtil.doCMPart("刘禹嘉"));
		assertEquals("Be Same", null, ServerUtil.doCMPart("Anonymous"));
		
	}

	@Ignore
	@Test
	public void testLicenseModule() {
		for (int i = 0; i < 10; i++) {
			assertEquals("In license", true,ServerUtil.doLiensePart());
		}
		assertEquals("No in license", false, ServerUtil.doLiensePart());
	}
	
	@Ignore
	@Test
	public void testFMModule() {
		ServerUtil.doFMPart(1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ServerUtil.doFMPart(2);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ServerUtil.changeFMDir("junittest_error.log");
		ServerUtil.doFMPart(1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ServerUtil.doFMPart(2);
	}
	
//	@Ignore
	@Test
	public void testPMModule() {
		ServerUtil.changePMIntv(1000);
		ServerUtil.startPM();
		log("PM Start.... at time: "+new Date());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ServerUtil.doPMPart("pm1", 13);
		ServerUtil.doPMPart("pm2", 23);
		ServerUtil.doPMPart("pm3", 1);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ServerUtil.doPMPart("pm4", 3);
		ServerUtil.doPMPart("pm2", 0);
		
		ServerUtil.stopPM();
		log("PM Stop.... at time: "+new Date());
		
		
	}
	
	private void log(String str) {
		System.out.println(str);
	}
}
