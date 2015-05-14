package com.eva.me.server;

import java.net.Socket;

public class ClientProcessThread extends Thread{
	
	private Socket clientSocket = null;
	
	public ClientProcessThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	
}
