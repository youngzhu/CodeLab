package com.youngzy.book.concurrency.ch07;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 7-25 将异常写入日志的 UncaughtExceptionHandler
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}
