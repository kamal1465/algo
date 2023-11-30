package com.kamals.algo.choodp.Ch4.frame;

import javax.swing.*;
import java.awt.*;

public class FrameTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      JButton helloButton = new JButton("Say Hello");
      JButton goodbyeButton = new JButton("Say Goodbye");

      final int FIELD_WIDTH = 20;
      JTextField textField = new JTextField(FIELD_WIDTH);
      textField.setText("Click a button!");

      frame.setLayout(new FlowLayout());

      frame.add(helloButton);
      frame.add(goodbyeButton);
      frame.add(textField);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
