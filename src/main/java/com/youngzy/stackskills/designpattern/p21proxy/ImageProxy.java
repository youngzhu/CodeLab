package com.youngzy.stackskills.designpattern.p21proxy;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageProxy implements Icon {
    // 被代理的真正对象
    ImageIcon imageIcon;
    URL imageUrl;
    Thread retrievalThread;
    boolean retrieving = false;

    public ImageProxy(URL url) {
        imageUrl = url;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (imageIcon != null) {
            imageIcon.paintIcon(c, g, x, y);
        } else {
            // 虚拟代理
            // 当大对象还未创建（完成）时，先给出提示
            g.drawString("图片正在加载，请稍后...", x + 300, y + 300);
            if (! retrieving) {
                retrieving = true;
                // 我们不希望挂起整个界面
                // 所以用另一个线程获取图像
                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        imageIcon = new ImageIcon(imageUrl, "网络图片");
                        // 当图像获取完成后，重绘
                        c.repaint();
                    }
                });

                retrievalThread.start();
            }
        }

    }

    @Override
    public int getIconWidth() {
        if (imageIcon != null) {
            return imageIcon.getIconWidth();
        }
        return 800;
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null) {
            return imageIcon.getIconHeight();
        }
        return 600;
    }
}
