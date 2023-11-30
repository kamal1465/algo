package com.kamals.algo.choodp.Ch8.umleditor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyEditorSupport;

/**
   A property editor for the MultiLineString type.
*/
public class MultiLineStringEditor extends PropertyEditorSupport
{
   public boolean supportsCustomEditor()
   {
      return true;
   }

   public Component getCustomEditor()
   {
      final MultiLineString value = (MultiLineString) getValue();
      final JTextArea textArea = new JTextArea(value.getText(),10, 40);
      textArea.getDocument().addDocumentListener(new
         DocumentListener()
         {
            public void insertUpdate(DocumentEvent e)
            {
               value.setText(textArea.getText());
               firePropertyChange();
            }
            public void removeUpdate(DocumentEvent e)
            {
               value.setText(textArea.getText());
               firePropertyChange();
            }
            public void changedUpdate(DocumentEvent e)
            {
            }
         });
      return new JScrollPane(textArea);
   }
}
