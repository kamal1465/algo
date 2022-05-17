package com.kamals.algo.idiom.proration;

import java.util.List;

public class Promotion
{
    private String code;
    private List<OrderEntry> entries;

    public Promotion(String code, List<OrderEntry> entries)
    {
        this.code = code;
        this.entries = entries;
    }

    public String getCode()
    {
        return code;
    }

    public List<OrderEntry> getEntries()
    {
        return entries;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("OFF ");

        stringBuilder.append(code).append(" ");

        if (entries != null)
        {
            stringBuilder.append("ON ");
            for (OrderEntry entry : entries)
            {
                stringBuilder.append(entry.getLineNo())
                        .append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
