/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Arthur
 */
public class SocketLink {
	private static final String SERVERIP = "127.0.0.1";
	private static final int SERVERPORT = 12234;
	private Socket mSocket = null;
	private BufferedReader mBufferedReader = null;
	private PrintWriter mPrintWriter = null;
	private InputStream iStream = null;

	public void login() throws IOException	{
		mSocket = new Socket(SERVERIP, SERVERPORT);
		iStream = mSocket.getInputStream();
		mBufferedReader = new BufferedReader(new InputStreamReader(iStream));
		mPrintWriter = new PrintWriter(mSocket.getOutputStream(), true);
	}
	
	public void sendText(String str) throws IOException {
		mPrintWriter.print(str);
		mPrintWriter.flush();
	}
	
	public String reciveByte() throws IOException {
		String recString = transInputStreamToStr();
		return recString;
	}

	public void logout() throws IOException {
		mPrintWriter.close();
		mBufferedReader.close();
		iStream.close();
		mSocket.close();
	}
	
	private String transInputStreamToStr() throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			final int BUFFER_SIZE = 1;
			byte[] data = new byte[BUFFER_SIZE];
			int m =0;
			int count =-1;
			while((count = iStream.read(data)) != -1) {
				byteArrayOutputStream.write(data);
				m++;
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] dataAll = byteArrayOutputStream.toByteArray();
		String result = "";
		try {
			result = new String(dataAll,0,dataAll.length,"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		result.trim();
		return result;
	}

}
