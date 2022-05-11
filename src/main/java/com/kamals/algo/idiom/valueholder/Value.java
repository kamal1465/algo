package com.kamals.algo.idiom.valueholder;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

class Value
{
    protected static final String DATE_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String TIMEZONE = "IST";

    private String token;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_ISO, timezone = TIMEZONE)
    private Date validTill;

    private Value(String token, Date validTill)
    {
        this.token = token;
        this.validTill = validTill;
    }

    public String getToken()
    {
        return token;
    }

    public Date getValidTill()
    {
        return validTill;
    }

    public static Value generateValue()
    {
        String token = UUID.randomUUID().toString();
        token = token.replaceAll("-", "");
        System.out.println(token);

        int validMins = new Random().nextInt(60);
        System.out.println(validMins);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, validMins);
        Date date = calendar.getTime();
        System.out.println(date);
        return new Value(token, date);
    }

    @Override
    public String toString()
    {
        return "Value{" + token + " validTill=" + validTill + '}';
    }
}
