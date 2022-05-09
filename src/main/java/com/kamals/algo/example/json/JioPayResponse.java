package com.kamals.algo.example.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;

public class JioPayResponse extends JioPayResponseInner
{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Error error;

    public static class Error
    {
        private String code;
        private String message;

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }
    }

    public Error getError()
    {
        return error;
    }

    public void setError(Error error)
    {
        this.error = error;
    }

    @Override
    public String toString()
    {
        String json;
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            //ObjectWriter objectWriter = objectMapper.writer();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            json = objectWriter.writeValueAsString(this);
        }
        catch (JsonProcessingException e)
        {
            json = "Error occurred";
        }
        return json;
    }

    @JsonIgnore
    public static <S extends JioPayResponse> S fromString(final String string, Class<S> sClass)
    {
        S s = null;
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            s = objectMapper.readValue(string, sClass);
        }
        catch (Exception e)
        {
            System.out.println("Error while de-serializing " + sClass + ": " + string + " : " + e.getMessage());
        }
        return s;
    }

    @JsonIgnore
    public static <S extends JioPayResponse> List<S> fromStringList(final String string, Class<S> sClass)
    {
        List<S> s = null;
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            s = objectMapper.readValue(string, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, sClass));
        }
        catch (Exception e)
        {
            System.out.println("Error while de-serializing List<" + sClass + ">: " + string);
        }
        return s;
    }

    @JsonIgnore
    public boolean isTokenInvalid()
    {
        return error != null && ("33001".equals(error.getCode()) || "33003".equals(error.getCode()) ||
                "33708".equals(error.getCode()) || "33303".equals(error.getCode()));
    }
}
