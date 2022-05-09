package com.kamals.algo.example.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class ArrayJsonDeserializer extends JsonDeserializer<List<JioPayResponseInner>>
{
    @Override
    public List<JioPayResponseInner> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException
    {
        InnerItems innerItems = jp.readValueAs(InnerItems.class);
        return innerItems.elements;
    }

    private static class InnerItems
    {
        List<JioPayResponseInner> elements;
    }
}
