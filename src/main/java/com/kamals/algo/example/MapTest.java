package com.kamals.algo.example;

import java.util.HashMap;
import java.util.Map;

public class MapTest
{
    public static void main(String[] args)
    {
        Map<String, String> dataMap = new HashMap<>();

        dataMap.put("232323_232", "");
        dataMap.put("26556_232", "");
        dataMap.put("356565_232", "");
        dataMap.put("565_232", "");
        dataMap.put("232323", "");
        dataMap.put("26556", "");

        for (String key : dataMap.keySet())
        {
            if (key.contains("_"))
            {
                String p = key.substring(0, key.indexOf("_"));

                System.out.println(p);

                System.out.println();
            }
        }
    }
}
