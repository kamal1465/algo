package com.kamals.algo.choodp.Ch8.umleditor;

import java.awt.*;

/**
   This enumeration defines line styles of various shapes.
*/
public enum LineStyle
{
   SOLID, DOTTED;

   /**
      Gets a stroke with which to draw this line style.
      @return the stroke object that strokes this line style
   */
   public Stroke getStroke()
   {
      if (this == DOTTED)
         return DOTTED_STROKE;
      return SOLID_STROKE;
   }

   private static Stroke SOLID_STROKE = new BasicStroke();
   private static Stroke DOTTED_STROKE = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] { 3.0f, 3.0f }, 0.0f);
}
