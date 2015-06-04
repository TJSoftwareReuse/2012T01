package com.team01.fm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ErrorUtil {
	private String error_list_path = "error_list";
	private String log_file_name = "error.log";
	private String log_file_path = "";
	private Integer last_error_id = -1;
	private Map< Integer, String > error_summary = new HashMap< Integer, String >();
	
	public ErrorUtil() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(error_list_path);
		} catch (FileNotFoundException e) {
			System.out.println("error list not exist");
			e.printStackTrace();
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader bd = new BufferedReader(isr);
		int num = 0;
		try {
			num = bd.read();
			bd.readLine();
		} catch (IOException e) {
			System.out.println("can not read the error list");
			e.printStackTrace();
		}
		
		for(int i=1; i<=num; i++){
			String summary = new String();
			try {
				summary = bd.readLine();
			} catch (IOException e) {
				System.out.println("can not read the error list");
				e.printStackTrace();
			}
			error_summary.put(i,summary);
		}
		try {
			bd.close();
		} catch (IOException e) {
			System.out.println("no file are opened");
			e.printStackTrace();
		}
	}
	
	public boolean set_error_path(String error_path) {
		String str = "";
		System.out.println(error_path);
		if(error_path.indexOf(error_path.length()-1)=='\\')
			str = error_path.substring(error_path.length()-1);
		else
			str = error_path;
		File file = new File(error_path);
		System.out.println(str);
		if(!file.exists()) {
			try {
				file.mkdirs();
				log_file_path = str + "\\";
			} catch (Exception e) {
				System.out.println("Filed to set the error path");
			}
		}
		else {
			log_file_path = str + "\\";
		}
		return true;
	}
	
	@SuppressWarnings("resource")
	public boolean report_an_error(Integer error_id) {
		if(error_id > error_summary.size())
			return false;
		if(error_id == last_error_id)
			return true;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(log_file_path + log_file_name, true);
		} catch (FileNotFoundException e) {
			System.out.println("can not open the log file");
			e.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		try {
			bw.write(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())+" "+error_summary.get(error_id));
			bw.newLine();
		} catch (IOException e) {
			System.out.println("failed to write the log");
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		last_error_id = error_id;
		return true;
	}
}
