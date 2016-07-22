package com.wei.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static volatile Server serverInstance = null;

	public static Server getInstance() {
		if (serverInstance == null) {
			synchronized (Server.class) {
				if (serverInstance == null) {
					serverInstance = new Server();
				}
			}
		}
		return serverInstance;
	}

	public void openServer() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1234);
			System.out.println("服务端开启："
					+ serverSocket.getInetAddress().getHostAddress() + ":"
					+ serverSocket.getLocalPort());
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("客户端连接："
						+ socket.getInetAddress().getHostAddress() + ":"
						+ socket.getPort());
				new ServerThread(socket).start();

			}
		} catch (IOException e) {
			System.out.println("Server IOException:" + e);
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				System.out.println("Server close IOException:" + e);
			}
		}
	}

}
