package com.eva.me.server;

import java.io.IOException;
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
		
		while (true) {
			Socket clientSocket = accept();
			ClientProcessThread clientThread = new ClientProcessThread(clientSocket);
		}
		
	}
	
	
	public static void startServer() {
		try {
			mServer = new Server();
			log("Start Server....");
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
