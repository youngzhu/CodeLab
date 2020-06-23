package com.youngzy.stackskills.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello UI");
        frame.getContentPane().add(label);

        JButton button = new JButton("Click me");
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("You clicks the button, didn't you ?");
            }
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
