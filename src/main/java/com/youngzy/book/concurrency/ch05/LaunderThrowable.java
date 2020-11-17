package com.youngzy.book.concurrency.ch05;

/**
 * 5-13
 */
public class LaunderThrowable {

    /**
     * 强制将为检查的Throwable转换为 RuntimeException
     *
     * 如果是 Error，直接抛出
     * 如果是 RuntimeException，返回它
     * 其他的，抛出 IllegalStateException
     *
     * @param throwable
     * @return
     */
    public static RuntimeException launderThrowable(Throwable throwable) {
      if (throwable instanceof RuntimeException) {
          return (RuntimeException) throwable;
      } else if (throwable instanceof Error) {
          throw (Error) throwable;
      } else {
          throw new IllegalStateException("Not unchecked", throwable);
      }
    }
}
