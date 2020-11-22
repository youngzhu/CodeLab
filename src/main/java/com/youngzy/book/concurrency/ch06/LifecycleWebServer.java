package com.youngzy.book.concurrency.ch06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 6-8 支持关闭的web服务
 */
public class LifecycleWebServer {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!executor.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(conn);
                    }
                });
            } catch (RejectedExecutionException e) {
                if (!executor.isShutdown()) {
                    log("task submission rejected", e);
                }
            }
        }
    }

    private void log(String msg, Exception e) {
        Logger.getAnonymousLogger().log(Level.WARNING, msg, e);
    }

    private void handleRequest(Socket conn) {
        Request request = readRequest(conn);
        if (isShutdownRequest(request))
            stop();
        else
            dispatchRequest(request);
    }

    private void dispatchRequest(Request request) {
    }

    private boolean isShutdownRequest(Request request) {
        return false;
    }

    private Request readRequest(Socket conn) {
        return null;
    }

    interface Request {
    }

    public void stop() {
        executor.shutdown();
    }
}
