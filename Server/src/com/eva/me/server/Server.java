package com.eva.me.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket{
	private final static int SERVER_PORT = 12234;
	private static Server mServer = null;
	public static Object serverLock = new Object(); 
	
	public static void log(String str) {
		System.out.println(str);
	}
	
	public Server() throws IOException {
		super(SERVER_PORT);
		log("Server start at InetAddress "+InetAddress.getLocalHost() + " : "+SERVER_PORT);
		while (true) {
			log("Ready for receive socket...");
			Socket clientSocket = accept();
			log("Socket received....");
			ClientProcessThread clientThread = new ClientProcessThread(clientSocket);
		}
		
	}
	
	
	public static void startServer() {
		try {
			log("Start Server....");
			mServer = new Server();
		} catch (IOException e) {
			log("Server Start Exception...");
			e.printStackTrace();
		}
	}
	
	public static void stopServer() {
		try {
			if (mServer != null) {
				mServer.close();
				log("Server Close......");
			}
		} catch (IOException e) {
			log("Server Stop Exception...");
			e.printStackTrace();
		}
	}
}
