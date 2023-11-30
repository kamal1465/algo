package com.kamals.algo.choodp;

import javax.swing.*;

public class DialogBox
{
    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(null, "Hello Car!", "Message",
                JOptionPane.INFORMATION_MESSAGE, new CarIcon(200));
    }
}
