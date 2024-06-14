package com.youngzy.stackskills.designpattern.p21proxy;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

public class ImageProxyTest {
    ImageComponent imageComponent;
    JFrame frame = new JFrame("图片浏览");
    JMenuBar menuBar;
    JMenu menu;
    Hashtable<String, String> images = new Hashtable<>();

    public static void main(String[] args) throws MalformedURLException {
        ImageProxyTest test = new ImageProxyTest();
    }

    public ImageProxyTest() throws MalformedURLException {

        images.put("ping-fan-shi-jie", "http://www.youngzy.com/wp-content/uploads/2020/06/pingfandeshijie.jpeg");
        images.put("hbm", "http://www.youngzy.com/wp-content/uploads/2020/09/hibernate-status.jpg");

        URL initURL = new URL(images.get("ping-fan-shi-jie"));
        menuBar = new JMenuBar();
        menu = new JMenu("精选");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        for (Map.Entry<String, String> entry : images.entrySet()) {
            String name = entry.getKey();
            JMenuItem menuItem = new JMenuItem(name);
            menu.add(menuItem);
            menuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e.getActionCommand());
                    imageComponent.setIcon(new ImageProxy(getImageUrl(e.getActionCommand())));
                    imageComponent.updateUI();
                }
            });
        }

        Icon icon = new ImageProxy(initURL);
        imageComponent = new ImageComponent(icon);
        frame.getContentPane().add(imageComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private URL getImageUrl(String name) {
        try {
            return new URL(images.get(name));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class ImageComponent extends JComponent {
        private Icon icon;

        public ImageComponent(Icon icon) {
            this.icon = icon;
        }

        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        @Override
        protected void paintChildren(Graphics g) {
            super.paintChildren(g);
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            int x = (800 - w)/2;
            int y = (600 - h)/2;
            icon.paintIcon(this, g, x, y);
        }
    }
}