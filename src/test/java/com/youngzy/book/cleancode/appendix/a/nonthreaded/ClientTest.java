package com.youngzy.book.cleancode.appendix.a.nonthreaded;

import java.io.IOException;
import java.net.Socket;

import com.youngzy.book.cleancode.appendix.a.MessageUtils;
import com.youngzy.book.cleancode.appendix.a.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ClientTest {
	private static final int PORT = 8009;
	private static final int TIMEOUT = 2000;
	
	Server server;
	Thread serverThread;
	
	@BeforeEach
	public void createServer() {
		try {
			server = new Server(PORT, TIMEOUT);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		serverThread = new Thread(server);
		serverThread.start();
	}
	
//	@Test(timeout = 10000)
	public void shouldRunInUnder10Sec() throws InterruptedException {
		Thread[] threads = new Thread[10];
		
		for (int i = 0; i < threads.length; ++i) {
			threads[i] = new Thread(new TrivialClient(i));
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; ++i) {
			threads[i].join();
		}
	}
	
	class TrivialClient implements Runnable {
		int clientNumber;
		
		TrivialClient(int clientNumber) {
			this.clientNumber = clientNumber;
		}

		@Override
		public void run() {

			try {
				connectSendReceive(clientNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void connectSendReceive(int clientNumber) throws Exception {

		System.out.printf("Client %2d: connecting\n", clientNumber);
		Socket socket = new Socket("localhost", PORT);
		
		System.out.printf("Client %2d: sending message\n", clientNumber);
		MessageUtils.sendMessage(socket, Integer.toString(clientNumber));
		
		String msg = MessageUtils.getMessage(socket);
		System.out.printf("Client %2d: getting reply: %s\n", clientNumber, msg);
		
		System.out.printf("Client %2d: finished\n", clientNumber);
		socket.close();
	}
	
	@AfterEach
	public void shutdownServer() {
		if (server != null) {
			server.stopProcessing();
			try {
				serverThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	

}
