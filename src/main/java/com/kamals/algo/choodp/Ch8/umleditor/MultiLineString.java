package com.kamals.algo.choodp.Ch8.umleditor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.StringTokenizer;

/**
   A string that can extend over multiple lines.
*/
public class MultiLineString implements Cloneable, Serializable
{
   /**
      Constructs an empty, centered, normal size multiline
      string that is not underlined.
   */
   public MultiLineString() 
   { 
      text = ""; 
      justification = CENTER;
      size = NORMAL;
      underlined = false;    
   }
   /**
      Sets the value of the text property.
      @param newValue the text of the multiline string
   */
   public void setText(String newValue) { text = newValue; }
   /**
      Gets the value of the text property.
      @return the text of the multiline string
   */
   public String getText() { return text; }
   /**
      Sets the value of the justification property.
      @param newValue the justification, one of LEFT, CENTER, 
      RIGHT
   */
   public void setJustification(int newValue) { justification = newValue; }
   /**
      Gets the value of the justification property.
      @return the justification, one of LEFT, CENTER, 
      RIGHT
   */
   public int getJustification() { return justification; }
   /**
      Gets the value of the underlined property.
      @return true if the text is underlined
   */
   public boolean isUnderlined() { return underlined; }
   /**
      Sets the value of the underlined property.
      @param newValue true to underline the text
   */
   public void setUnderlined(boolean newValue) { underlined = newValue; }
   /**
      Sets the value of the size property.
      @param newValue the size, one of SMALL, NORMAL, LARGE
   */
   public void setSize(int newValue) { size = newValue; }
   /**
      Gets the value of the size property.
      @return the size, one of SMALL, NORMAL, LARGE
   */
   public int getSize() { return size; }
   
   public String toString()
   {
      return text.replace('\n', '|');
   }

   private void setLabelText()
   {
      StringBuffer prefix = new StringBuffer();
      StringBuffer suffix = new StringBuffer();
      StringBuffer htmlText = new StringBuffer();
      prefix.append("&nbsp;");
      suffix.insert(0, "&nbsp;");
      if (underlined) 
      {
         prefix.append("<u>");
         suffix.insert(0, "</u>");
      }
      if (size == LARGE)
      {
         prefix.append("<font size=\"+1\">");
         suffix.insert(0, "</font>");
      }
      if (size == SMALL)
      {
         prefix.append("<font size=\"-1\">");
         suffix.insert(0, "</font>");
      }
      htmlText.append("<html>");
      StringTokenizer tokenizer = new StringTokenizer(text, "\n");
      boolean first = true;
      while (tokenizer.hasMoreTokens())
      {
         if (first) first = false; else htmlText.append("<br>");
         htmlText.append(prefix);
         htmlText.append(tokenizer.nextToken());
         htmlText.append(suffix);
      }      
      htmlText.append("</html>");
      label.setText(htmlText.toString());
      if (justification == LEFT) label.setHorizontalAlignment(JLabel.LEFT);
      else if (justification == CENTER) label.setHorizontalAlignment(JLabel.CENTER);
      else if (justification == RIGHT) label.setHorizontalAlignment(JLabel.RIGHT);
   }
   
   /**
      Gets the bounding rectangle for this multiline string.
      @param g2 the graphics context
      @return the bounding rectangle (with top left corner (0,0))
   */
   public Rectangle2D getBounds(Graphics2D g2)
   {
      if (text.length() == 0) return new Rectangle2D.Double();
      setLabelText();
      Dimension dim = label.getPreferredSize();       
      return new Rectangle2D.Double(0, 0, dim.getWidth(), dim.getHeight());
   }

   /**
      Draws this multiline string inside a given rectangle
      @param g2 the graphics context
      @param r the rectangle into which to place this multiline string
   */
   public void draw(Graphics2D g2, Rectangle2D r)
   {
      setLabelText();
      label.setFont(g2.getFont());
      label.setBounds(0, 0, (int) r.getWidth(), (int) r.getHeight());
      g2.translate(r.getX(), r.getY());
      label.paint(g2);
      g2.translate(-r.getX(), -r.getY());        
   }

   public Object clone()
   {
      try
      {
         return super.clone();
      }
      catch (CloneNotSupportedException exception)
      {
         return null;
      }
   }

   public static final int LEFT = 0;
   public static final int CENTER = 1;
   public static final int RIGHT = 2;
   public static final int LARGE = 3;
   public static final int NORMAL = 4;
   public static final int SMALL = 5;

   private static final int GAP = 3;

   private String text;
   private int justification;
   private int size;
   private boolean underlined;
   
   private static JLabel label = new JLabel();
}
