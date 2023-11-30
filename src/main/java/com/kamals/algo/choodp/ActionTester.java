package com.kamals.algo.choodp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ActionTester
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final int FIELD_WIDTH = 20;
        final JTextField textField = new JTextField(FIELD_WIDTH);
        textField.setText("Click a button!");

        JButton helloButton = new JButton("Say Hello");
        JButton goodbyeButton = new JButton("Say Goodbye");

        helloButton.addActionListener(e -> textField.setText("Hello, World!"));

        goodbyeButton.addActionListener(e -> textField.setText("Goodbye, World!"));

        ActionListener timedAction = e -> textField.setText(new Date().toString());
        final int DELAY = 1000;
        Timer t = new Timer(DELAY, timedAction);
        t.start();

        new Timer(1000, e -> System.out.println("Hello!")).start();

        frame.setLayout(new FlowLayout());

        frame.add(helloButton);
        frame.add(goodbyeButton);
        frame.add(textField);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
