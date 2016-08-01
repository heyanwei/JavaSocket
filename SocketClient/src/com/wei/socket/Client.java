package com.wei.socket;

import java.net.Socket;

public class Client {
	
	public final static String IPAddr="localhost";
	public final static int PORT=1234;
	
	public Socket socket;
	
	private static volatile Client clientInstance=null;
	
	public static Client getInstance(){
		if (clientInstance==null) {
			synchronized (Client.class) {
				if (clientInstance==null) {
					clientInstance=new Client();
				}
			}
		}
		return clientInstance;
	}
	
	public Client(){		
		
	}
	
	public void connectServer(){
		
		try {
			socket = new Socket(IPAddr, PORT);
			
			new ClientThread(socket).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
