package com.eva.me.server;

import java.net.Socket;

public class ClientProcessThread extends Thread{
	
	private Socket clientSocket = null;
	
	public static void log(String logContent) {
		System.out.println(logContent);
	}
	
	public ClientProcessThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		super.run();
		
		log("\nNew Client Request Process Thread create.....\n\n");
		
		
		
	}
	
}
