package com.youngzy.book.concurrency.ch06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 6-1 串行的Web服务
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket conn = socket.accept();
            handleRequest(conn);
        }
    }

    private static void handleRequest(Socket conn) {
    }
}
