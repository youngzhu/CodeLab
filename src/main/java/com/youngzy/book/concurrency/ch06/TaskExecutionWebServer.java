package com.youngzy.book.concurrency.ch06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 6-4 基于线程池的web服务
 */
public class TaskExecutionWebServer {
    private static final int NUM_THREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NUM_THREADS);

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

            exec.execute(task);
        }
    }

    private static void handleRequest(Socket conn) {
    }

}
