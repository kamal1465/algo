package com.kamals.algo.example.json;

import java.util.List;

public class OfferResponse extends JioPayResponse
{
    private String idempotentKey;
    private String initiatingEntityTimestamp;
    //@JsonDeserialize(using = ArrayJsonDeserializer.class)
    private List<Offer> offers;
    private Offer offer;
    private String intentId;
    private String invoiceNumber;

    public List<Offer> getOffers() {
        return offers;
    }

    public String getIdempotentKey()
    {
        return idempotentKey;
    }

    public void setIdempotentKey(String idempotentKey)
    {
        this.idempotentKey = idempotentKey;
    }

    public String getInitiatingEntityTimestamp()
    {
        return initiatingEntityTimestamp;
    }

    public void setInitiatingEntityTimestamp(String initiatingEntityTimestamp)
    {
        this.initiatingEntityTimestamp = initiatingEntityTimestamp;
    }

    public void setOffers(List<Offer> offers)
    {
        this.offers = offers;
    }

    public Offer getOffer()
    {
        return offer;
    }

    public void setOffer(Offer offer)
    {
        this.offer = offer;
    }

    public String getIntentId()
    {
        return intentId;
    }

    public void setIntentId(String intentId)
    {
        this.intentId = intentId;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

}
