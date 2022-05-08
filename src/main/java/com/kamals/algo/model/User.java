package com.kamals.algo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

public class User
{
    private UUID uuid;

    @NotBlank
    private String name;

    private String mobileNumber;

    private String emailId;

    public User(@JsonProperty("id") UUID uuid,
                @JsonProperty("name") String name,
                @JsonProperty("mobileNumber") String mobileNumber,
                @JsonProperty("emailId") String emailId)
    {
        this.uuid = uuid;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public String getName()
    {
        return name;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public String getEmailId()
    {
        return emailId;
    }
}
