package com.kamals.algo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "mobile")
    private String mobileNumber;

    @Column(name = "email")
    private String emailId;

    public User()
    {
    }

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

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }
}
