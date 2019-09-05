package com.youngzy.book.cleancode.appendix.a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ThreadedServer implements Runnable {
	ServerSocket serverSocket;
	volatile boolean keepProcessing = true;
	
	public ThreadedServer(int port, int millisecondsTimeout) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(millisecondsTimeout);
	}

	public void run() {
		System.out.println("Server starting...");
		
		while(keepProcessing) {
			System.out.println("accepting client...");
			
			Socket socket;
			try {
				socket = serverSocket.accept();
				System.out.println("got client.");
				
				process(socket);
			} catch (Exception e) {
				handle(e);
			}
		}

	}
	
	public void stopProcessing() {
		keepProcessing = false;
		colseIgnoringException(serverSocket);
	}

	private void colseIgnoringException(ServerSocket serverSocket) {

		if (null != serverSocket) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	private void handle(Exception e) {
		if (! (e instanceof SocketException)) {
			e.printStackTrace();
		}
	}

	private void process(final Socket socket) {

		if (null == socket) {
			return;
		}
		
		// 跟非线程的区别就在这里
		// 将之前的代码用线程类封装起来
		Runnable clientHandler = new Runnable() {
			public void run() {
				try {
					System.out.println("[Server] getting message");
					String msg = MessageUtils.getMessage(socket);
					System.out.printf("[Server] got message: %s\n", msg);
					Thread.sleep(1000);
					
//			System.out.printf("[Server] sending reply: %s\n", msg);
					MessageUtils.sendMessage(socket, "Processed the message: " + msg);
					System.out.println("[Server] sended");
					
					colseIgnoringException(socket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread clientConn = new Thread(clientHandler);
		clientConn.start();
		
	}

	private void colseIgnoringException(Socket socket) {

		if (null != socket) {
			try {
				socket.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

}
