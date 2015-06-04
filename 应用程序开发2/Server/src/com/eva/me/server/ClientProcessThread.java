package com.eva.me.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClientProcessThread extends Thread{
	
	private final String encoding = "UTF-8";
	
	private Socket clientSocket = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	private String response = null;
	private final String request; 
	
	
	public static void log(String logContent) {
		System.out.println(logContent);
	}
	
	public ClientProcessThread(Socket cSocket) {
		this.clientSocket = cSocket;
		try {
			clientSocket.setSoTimeout(2000);
		} catch (SocketException e1) {
			log("timeout....");
			e1.printStackTrace();
		}
		try {
			inputStream = clientSocket.getInputStream();
			outputStream = clientSocket.getOutputStream();
			log("input stream output stream get....");
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
		log("Client request: |"+request+"|");
		
		
	}
	
	private String transInputStreamToStr(InputStream iStream) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			final int BUFFER_SIZE = 1;
			byte[] data = new byte[BUFFER_SIZE];
			int m =0;
			int count =-1;
			while((count = iStream.read(data)) != -1) {
					byteArrayOutputStream.write(data);
					log("m ="+m);
					m++;
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] dataAll = byteArrayOutputStream.toByteArray();
		String result = "";
		try {
			result = new String(dataAll,0,dataAll.length,encoding);
			result = new String(dataAll,0,dataAll.length);
//		result = new String(dataAll);
//		log("length = "+dataAll.length);
			
		} catch (UnsupportedEncodingException e) {
			log("UnsupportedEncodingException.....");
			e.printStackTrace();
		}
		result.replaceAll("\n|\r", "");
		result.trim();
		return result;
	}

	private void transStringToOutputStream(final String strContent, OutputStream oStream) {
//		byte[] data = strContent.getBytes();
//		try {
//			oStream.write(data);
//			oStream.flush();
//		} catch (IOException e) {
//			System.out.println("Write strContent: "+strContent+" to output stream error...");			
//			e.printStackTrace();
//		}
		PrintStream out = new PrintStream(oStream);
		out.println(strContent);
		out.flush();
		out.close();
		
	}
	
	@Override
	public void run() {
		super.run();
		
		synchronized (Server.serverLock) {
			log("\nNew Client Request Process Thread create.....\n\n");
			processClientInfo();
			log("\nNew Client Request Process Complete.....\n\n");
			
			try {
				inputStream.close();
				outputStream.close();
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("Stream.. close .. error...");
				e.printStackTrace();
			}
		}
		
	}

	private void processClientInfo() {
		
		//receive message
		//PM rec+1
		//License can provide service: true or fasle
		ServerUtil.receivedNum ++ ;
		log("processClientInfo : "+"receive num ++");
		boolean canProvideService = ServerUtil.doLiensePart();
		log("processClientInfo : "+" can provide service: "+canProvideService);
		
		if (canProvideService) {
			//if true
			//FM provide service
			//PM proSer + 1
			//CM return group name
			//PM retMsg + 1
			ServerUtil.doFMPart(1);
			log("processClientInfo : "+"do FM part...");
			ServerUtil.supportNum ++ ;
			log("processClientInfo : "+"support num ++");
			ServerUtil.doPMPart("provide_service", ServerUtil.supportNum);
			log("processClientInfo : "+"do PM part, provide_service update");
			String groupName = ServerUtil.doCMPart(request);
			log("processClientInfo : "+"do CM part, find group name... through ID: "+request);
			if (groupName == null) {
				log("processClientInfo : "+"do CM part, group name is null ... ID: "+request+" do not in any group");
				response = "Group Name Do Not Exist!";
			}else {
				log("processClientInfo : "+"do CM part, group name is "+groupName+" ... ID: "+request);
				response = groupName;
			}
		} else {
			//if false
			//FM do not provide service
			//PM rejSer + 1
			// return refuse service ...
			//PM retMsg + 1
			ServerUtil.doFMPart(2);
			log("processClientInfo : "+"do FM part...");
			ServerUtil.rejectNum ++ ;
			log("processClientInfo : "+"reject num ++");
			ServerUtil.doPMPart("reject_provide_service", ServerUtil.rejectNum);
			log("processClientInfo : "+"do PM part, reject_provide_service update");
			response = "Reject Provide Service, Check Your License!";
		}
		
		//return reponse
		transStringToOutputStream(response+"\n", outputStream);
		log("Response Success: "+response);
		
		ServerUtil.sendBackNum ++ ;
		log("processClientInfo : "+"sendBack num ++");
		ServerUtil.doPMPart("send_back", ServerUtil.sendBackNum);
		log("processClientInfo : "+"do PM part, send_back update");
	}
	
}
