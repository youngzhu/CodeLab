package com.youngzy.book.concurrency.ch09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
public class ListenerExamples {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    /*
    9-3 简单的时间监听
     */
    private final JButton colorButton = new JButton("Change color");
    private final Random random = new Random();

    private void backgroundRandom() {
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorButton.setBackground(new Color(random.nextInt()));
            }
        });
    }

    /*
    9-4 将一个长时间任务绑定到一个可视化组件
     */
    private final JButton computeButton = new JButton("Big computation");

    private void longRunningTask() {
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // do big computation
                    }
                });
            }
        });
    }

    /*
    9-5 支持用户反馈的长时间任务
     */
    private final JButton button = new JButton("Do");
    private final JLabel label = new JLabel("idle");

    private void longRunningTaskWithFeedback() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);
                label.setText("busy");
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // do big computation
                        } finally {
                            GuiExecutor.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    button.setEnabled(true);
                                    label.setText("idle");
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /*
    9-6 取消一个长时间任务
     */
    private final JButton startButton = new JButton("Start");
    private final JButton cancelButton = new JButton("Cancel");
    private Future<?> runningTask = null; // 线程封闭

    private void taskWithCancellation() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (runningTask != null) {
                    runningTask = executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            while (moreWork()) {
                                if (Thread.currentThread().isInterrupted()) {
                                    cleanupPartialWork();
                                    break;
                                }
                                doSomeWork();
                            }
                        }

                        private boolean moreWork() {
                            return false;
                        }

                        private void cleanupPartialWork() {}

                        private void doSomeWork() {}
                    });
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (runningTask != null) {
                    runningTask.cancel(true);
                }
            }
        });
    }

    /*
    9-8 通过 BackgroundTask 来执行长时间的并且可取消的任务
     */
    private void runInBackground(final Runnable task) {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                class CancelListener implements ActionListener {
                    BackgroundTask<?> task;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (task != null)
                            task.cancel(true);
                    }
                }

                final CancelListener listener = new CancelListener();
                listener.task = new BackgroundTask<Object>() {
                    @Override
                    protected Object compute() throws Exception {
                        while (moreWork() && ! isCancelled())
                            doSomeWork();
                        return null;
                    }

                    private boolean moreWork() {
                        return false;
                    }

                    private void doSomeWork() {}

                    @Override
                    protected void onCompletion(Object result, Throwable throwable, boolean cancelled) {
                        cancelButton.removeActionListener(listener);
                        label.setText("done");
                    }
                };

                cancelButton.addActionListener(listener);
                executorService.execute(task);
            }
        });
    }
}
