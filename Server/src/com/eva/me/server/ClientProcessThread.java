package com.eva.me.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientProcessThread extends Thread{
	
	private final String encoding = "utf-8";
	
	private Socket clientSocket = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	private final String response = null;
	private final String request; 
	
	
	public static void log(String logContent) {
		System.out.println(logContent);
	}
	
	public ClientProcessThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			inputStream = clientSocket.getInputStream();
			outputStream = clientSocket.getOutputStream();
		} catch (IOException e) {
			log("inputStream | outputStream init error....");
			e.printStackTrace();
		}
		
		InetAddress inetAddress = clientSocket.getInetAddress();
		int port = clientSocket.getPort();
		log("Client connect in...\n" +
				"Client info:\n" +
				"\tIP:PORT = "+inetAddress+" : "+port);
		
		request = transInputStreamToStr(inputStream);
		
	}
	
	private String transInputStreamToStr(InputStream iStream) {
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
			System.out.println("UnsupportedEncodingException.....");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public void run() {
		super.run();
		
		synchronized (Server.serverLock) {
			log("\nNew Client Request Process Thread create.....\n\n");
			processClientInfo();
			log("\nNew Client Request Process Complete.....\n\n");
		}
		
	}

	private void processClientInfo() {
		
	}
	
}
