package com.eva.me.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends ServerSocket{
	private final static int SERVER_PORT = 12234;
	private static Server mServer = null;
	
	public Server() throws IOException {
		super(SERVER_PORT);
		
		while (true) {
			
		}
		
	}
	
	
	
	
	public static void startServer() {
		try {
			mServer = new Server();
		} catch (IOException e) {
			System.out.println("Server Start Exception...");
			e.printStackTrace();
		}
	}
	
	public static void stopServer() {
		try {
			if (mServer != null) {
				mServer.close();
			}
		} catch (IOException e) {
			System.out.println("Server Stop Exception...");
			e.printStackTrace();
		}
	}
}
