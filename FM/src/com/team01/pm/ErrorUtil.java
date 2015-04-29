package com.team01.pm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	private String log_file_path = "error.log";
	private Map< Integer, String > error_summary = new HashMap< Integer, String >();
	
	public ErrorUtil() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(error_list_path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			System.out.println("can not read the error list");
			e.printStackTrace();
		}
		
		for(int i=1; i<=num; i++){
			String summary = new String();
			try {
				summary = bd.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("can not read the error list");
				e.printStackTrace();
			}
			error_summary.put(i,summary);
		}
		try {
			bd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("no file are opened");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public boolean report_an_error(Integer error_id) {
		if(error_id > error_summary.size())
			return false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(log_file_path, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("can not open the log file");
			e.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		try {
			bw.write(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())+" "+error_summary.get(error_id));
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to write the log");
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
