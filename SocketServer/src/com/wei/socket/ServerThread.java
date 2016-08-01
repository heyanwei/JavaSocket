package com.wei.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ServerThread extends Thread {
	
	Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public ServerThread(Socket socket) {
		this.socket=socket;
	}
	
	public void closeSocket(){
		if (socket!=null) {
			try {
				System.out.println("客户端关闭连接...");
				dis.close();
				dos.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void run() {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			//dos.write("hello,client...".getBytes("gbk"));
			byte[] buf=new byte[1024];
			int buf_result_len=0;
			while((buf_result_len=dis.read(buf))>0){
				byte[] buf_result=new byte[buf_result_len];
				ByteBuffer bbf=ByteBuffer.wrap(buf);
				bbf.get(buf_result);
				System.out.println("recv:"+new String(buf_result));
				dos.write("已收到".getBytes("gbk"));
			}
			closeSocket();
		} catch (Exception e) {
			e.printStackTrace();
			closeSocket();
		}
		
	}

}
