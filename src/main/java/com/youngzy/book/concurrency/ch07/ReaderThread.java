package com.youngzy.book.concurrency.ch07;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 7-11 通过覆写 interrupt 方法将非标准的取消操作封装在 Thread 中
 */
public class ReaderThread extends Thread {
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {

        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) {
            // 允许线程退出
        }
    }

    public void processBuffer(byte[] buf, int count) {

    }
}
