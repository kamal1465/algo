package com.kamals.algo.example.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

public class JioPayResponseInner
{
    protected static final String DATE_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String TIMEZONE = "IST";

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> additionalProperties;

    public Map<String, Object> getAdditionalProperties()
    {
        return additionalProperties;
    }

    @JsonAnySetter
    public void setMap(String key, Object value)
    {
        if (additionalProperties == null)
        {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(key, value);
    }
}
