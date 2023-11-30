package com.kamals.algo.choodp.Ch4.action1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FIELD_WIDTH = 20;
      final JTextField textField = new JTextField(FIELD_WIDTH);
      textField.setText("Click a button!");

      JButton helloButton = new JButton("Say Hello");

      helloButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               textField.setText("Hello, World!");
            }
         });

      JButton goodbyeButton = new JButton("Say Goodbye");

      goodbyeButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               textField.setText("Goodbye, World!");
            }
         });

      frame.setLayout(new FlowLayout());

      frame.add(helloButton);
      frame.add(goodbyeButton);
      frame.add(textField);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
