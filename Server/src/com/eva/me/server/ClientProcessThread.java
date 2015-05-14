package com.eva.me.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientProcessThread extends Thread{
	
	private Socket clientSocket = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	
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
