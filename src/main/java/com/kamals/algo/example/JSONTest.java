package com.kamals.algo.example;

import org.json.JSONObject;

public class JSONTest
{
    public static void main(String[] args) throws Exception
    {
        String responseBody = "{\n" +
                "  \"id\": {\n" +
                "    \"$oid\": \"5e943e98727ab0704f31bda2\"\n" +
                "  },\n" +
                "  \"full_name\": \"Not Defined\",\n" +
                "  \"email\": \"info@reward360.co\",\n" +
                "  \"mobile\": null,\n" +
                "  \"access_token\": \"-CMttTyQSwaaM0TkeLCRhUPXEDQ3aZRKIqFm3YUVwEc\",\n" +
                "  \"refresh_token\": \"KdPq-3hfxkjHL5aJY0kmqXcqL4GeM0M7FjbpAO7Wlb4\",\n" +
                "  \"expires_in\": 7776000\n" +
                "}";
        System.out.println(responseBody);
        JSONObject jsonObject = new JSONObject(responseBody);

        String token = jsonObject.get("access_token").toString();
        System.out.println(token);
    }
}
