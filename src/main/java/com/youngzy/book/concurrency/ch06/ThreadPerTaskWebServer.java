package com.youngzy.book.concurrency.ch06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 6-2 为每个请求启动一个新的线程（不要这么做）
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket conn = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(conn);
                }
            };
            
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket conn) {
    }
}
