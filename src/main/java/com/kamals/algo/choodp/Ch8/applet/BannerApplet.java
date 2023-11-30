package com.kamals.algo.choodp.Ch8.applet;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class BannerApplet extends Applet
{
   public void init()
   {
      message = getParameter("message");
      String fontname = getParameter("fontname");
      int fontsize = Integer.parseInt(getParameter("fontsize"));
      delay = Integer.parseInt(getParameter("delay"));
      font = new Font(fontname, Font.PLAIN, fontsize);
      Graphics2D g2 = (Graphics2D) getGraphics();
      FontRenderContext context = g2.getFontRenderContext();
      bounds = font.getStringBounds(message, context);
      
      timer = new Timer(delay, new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               start--;
               if (start + bounds.getWidth() < 0) 
                  start = getWidth();
               repaint();
            }
         });
   }

   public void start()
   {
      timer.start();
   }

   public void stop()
   {
      timer.stop();
   }

   public void paint(Graphics g)
   {
      g.setFont(font);
      g.drawString(message, start, (int) -bounds.getY());
   }

   private Timer timer;
   private int start;
   private int delay;
   private String message;
   private Font font;
   private Rectangle2D bounds;
}
