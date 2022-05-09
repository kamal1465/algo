package com.kamals.algo.example;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleTest
{

    public static void main(String[] args)
    {
        Double x = 10.0;

        int y = x.compareTo(0D);

        System.out.println(y);
        //testPrecision();
        /*String s = "008";

        double x = Double.valueOf(s);

        double y = x / 12;

        y = new BigDecimal(y).setScale(2, RoundingMode.DOWN).doubleValue();

        System.out.println(y);

        convert();*/
    }

    private static void testDivide()
    {
        BigDecimal discount = new BigDecimal(2000).setScale(2, RoundingMode.HALF_UP);
        BigDecimal subtotal = new BigDecimal(62990);
        BigDecimal discountRatio = discount.divide(subtotal, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        BigDecimal prorated = discountRatio.multiply(subtotal);

        System.out.println(discount);
        System.out.println(subtotal);
        System.out.println(discountRatio);
        System.out.println(prorated);
    }

    private static void testDivide2()
    {
        double discount = 2000.0;
        double subtotal = 62990.0;
        double price = 62990.0;
        double discountRatio = price / subtotal;
        double prorated = discountRatio * discount;

        System.out.println(discount);
        System.out.println(subtotal);
        System.out.println(discountRatio);
        System.out.println(prorated);
    }

    private static void testDivide3()
    {
        BigDecimal discount = new BigDecimal(2000);
        BigDecimal effectivePrice = new BigDecimal(62990);
        double subtotal = 62990.0;
        double ratio = effectivePrice.doubleValue() / subtotal;
        double discountRatio = discount.doubleValue() * ratio;

        BigDecimal proratedDiscountBD = new BigDecimal(discountRatio).setScale(2, RoundingMode.HALF_UP);

        System.out.println(discount);
        System.out.println(ratio);
        System.out.println(discountRatio);
        System.out.println(proratedDiscountBD);
    }

    private static void convert()
    {
        String months = "029";
        String s = "";
        if (StringUtils.isNumeric(months))
        {
            int m = Integer.parseInt(months);
            if (m > 0)
            {
                int y = m / 12;
                m = m % 12;
                if (y > 0)
                {
                    s = y + " Year";
                    if (y > 1)
                    {
                        s += "s";
                    }
                    s += " ";
                }
                if (m > 0)
                {
                    s += (m + " Month");
                    if (m > 1)
                    {
                        s += "s";
                    }
                }
            }
        }
        System.out.println(s);
    }

    private static void testPrecision()
    {
        double d = 0.1;
        BigDecimal bd = new BigDecimal(String.valueOf(d)).setScale(100, RoundingMode.HALF_EVEN).stripTrailingZeros();
        BigDecimal bd2 = new BigDecimal(d).setScale(100, RoundingMode.HALF_EVEN).stripTrailingZeros();

        System.out.println(d);
        System.out.println(bd);
        System.out.println(bd2);
    }
}
