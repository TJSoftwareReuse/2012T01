package com.eva.me.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private final static int port = 12234;
	private final static String encoding = "utf-8";
	
	public static void log (String str) {
		System.out.println("Client : "+str);
	}
 	
	public  static void main (String [] args) {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getByName("192.168.1.105");
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException");
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket clientSocket = new Socket(inetAddress, port);
				clientSocket.setSoTimeout(4000);
				
				log("New Client Socket Instance init");
				
				OutputStream oStream = clientSocket.getOutputStream();
				InputStream iStream = clientSocket.getInputStream();
				String memName = new Scanner(System.in).nextLine();
				if (memName.equals("EXIT")) {
					break;
				}
				transStringToOutputStream(memName, oStream);
				String  result = transInputStreamToStr(iStream);
				log("result: "+result);
				
				oStream.close();
				iStream.close();
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("socket init exception");
				e.printStackTrace();
			}
		}
		log("End");
	}
	

	private static String transInputStreamToStr(InputStream iStream) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			final int BUFFER_SIZE = 1024;
			byte[] data = new byte[BUFFER_SIZE];
			int count =-1;
			while((count = iStream.read(data)) != -1) {
					byteArrayOutputStream.write(data);
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] dataAll = byteArrayOutputStream.toByteArray();
		String result = "";
		try {
			result = new String(dataAll,0,dataAll.length,encoding);
		} catch (UnsupportedEncodingException e) {
			log("UnsupportedEncodingException.....");
			e.printStackTrace();
		}
		
		return result;
	}

	private static void transStringToOutputStream(final String strContent, OutputStream oStream) {
		byte[] data = strContent.getBytes();
		try {
			oStream.write(data);
			oStream.flush();
		} catch (IOException e) {
			System.out.println("Write strContent: "+strContent+" to output stream error...");			
			e.printStackTrace();
		}
	}
	
}
