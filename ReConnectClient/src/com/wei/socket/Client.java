package com.wei.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Client {	

	public final static String IPAddr="192.168.1.100";
	public final static int PORT=1234;
	
	public int connect_flag=0;
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public Client(){
		
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public void openTerminal(){
		new Thread(){
			@Override
			public void run() {
				try {	
					while(true){
						
						if (connect_flag==1) {
							System.out.println("qwe1");
							dos.write("ahsu".getBytes());
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}.start();
	}
	
	public void openSocket(){
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						socket = new Socket(IPAddr, PORT);
						dis = new DataInputStream(socket.getInputStream());
						dos = new DataOutputStream(socket.getOutputStream());
						
						connect_flag=1;
						
						byte[] buf=new byte[1024];
						int buf_result_len=0;
						
						while(true){
							try {
								buf_result_len=dis.read(buf);
								if (buf_result_len==-1) {
									break;
								}
								
								connect_flag=1;
								
								byte[] buf_result=new byte[buf_result_len];
								ByteBuffer bbf=ByteBuffer.wrap(buf);
								bbf.get(buf_result);
								System.out.println("recv:"+new String(buf_result));
							} catch (Exception e) {
								e.printStackTrace();
								connect_flag=0;
								break;
							}										
						}
					} catch (UnknownHostException e) {
						e.printStackTrace();
						connect_flag=0;
					} catch (IOException e) {
						e.printStackTrace();
						connect_flag=0;
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						connect_flag=0;
					}
				}
			}
		}.start();
		
	}

}
