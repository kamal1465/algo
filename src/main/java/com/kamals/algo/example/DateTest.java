package com.kamals.algo.example;

import java.util.Calendar;
import java.util.Date;

public class DateTest
{
    private static Date getInterval(int hour)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.HOUR, -hour);
        Date cutOffTime = calendar.getTime();
        System.out.println(cutOffTime);
        return cutOffTime;
    }

    public static void main(String[] args) throws Exception
    {
        getInterval(2);
//        Date date = new Date();

//        TimeZone timeZone = TimeZone.getTimeZone("IST");
//
//        System.out.println(date);
//        for (int i = 0; i < 10; i++)
//        {
//            System.out.println(Calendar.getInstance(timeZone).getTime());
//            Thread.sleep(2000);
//        }
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));

        System.out.println(sdf.format(date));

        String transactionDate = "2020-12-14 16:39:21.000";
        Date txnDate = null;
        final String dateString = transactionDate.substring(0, transactionDate.lastIndexOf("-") + 5);
        final String timeString = transactionDate.substring(dateString.length());
        System.out.println(dateString);
        System.out.println(timeString);
        final String dateTimeTobeFormatted = dateString + " " + timeString;
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        txnDate = simpleDateFormat.parse(transactionDate);

        System.out.println(txnDate);*/
    }
}
