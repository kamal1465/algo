package com.kamals.algo.example;

import java.util.Scanner;

public class RegExTest
{
    public static void main(String[] args)
    {
        testRegex();
    }
    public static void test1()
    {
        String priceRangeQueryRegex = "(?i)(.*\\s+)?under\\s+(rs\\.?\\s*)?(\\d+(\\.\\d+)?k?)(\\s*rs(\\.)?)?(\\s+.*)?";

        String DISCOUNT_QUERY_REGEX = "(?i)(^|.*\\s+)discount(.*)";
        String LANDLINE_REGEX = "^(91|0)?[1-9](?=0*[1-9])(\\d{9})$";
        String EMAIL_REGEX = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";

        String query = null;

        Scanner scanner = new Scanner(System.in);

        do
        {
            query = scanner.nextLine();
            System.out.println(query.matches(EMAIL_REGEX));
            /*if (query.matches(DISCOUNT_QUERY_REGEX))
            {
                String price = query.replaceAll(priceRangeQueryRegex, "$3");
                String qry = query.replaceAll(priceRangeQueryRegex, "$1$7");

                for (int i = 0; i < 8; i++)
                {
                    //System.out.println(i + "=" + query.replaceAll(priceRangeQueryRegex, "$" + i));
                }
                System.out.println(price + "->" + qry);
            }
            else
            {
                System.out.println(query);
            }*/
        } while (!query.equalsIgnoreCase("bye"));
    }

    private static void testRegex()
    {
        String CSV_REGEX = "[-a-zA-Z0-9\\.]*";
        String s = "ckuscnewu...1289127891w7kcnkwnbck--jwc----";
        System.out.println(s.matches(CSV_REGEX));
    }
}
