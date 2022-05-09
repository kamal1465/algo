package com.kamals.algo.example.json;

public class Offer extends JioPayResponseInner
{
    private String code;
    private String name;
    private String description;
    private Integer status;
    private Integer eligibility;
    private Integer offerType;
    private String type;
    private String startDate;
    private String endDate;
    private Amount cashBackAmount;
    private DiscountUnit cashBackDiscountUnit;
    private Amount instantCashBackAmount;
    private DiscountUnit instantDiscountUnit;
    private Double transactionAmount;
    private String referenceId;
    private String termsConditions;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public static class Amount extends JioPayResponseInner
    {
        private double amount;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount)
        {
            this.amount = amount;
        }
    }

    public static class DiscountUnit extends JioPayResponseInner
    {
        private float type;
        private double value;

        public float getType() {
            return type;
        }

        public double getValue() {
            return value;
        }

        public void setType(float type)
        {
            this.type = type;
        }

        public void setValue(double value)
        {
            this.value = value;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Amount getCashBackAmount() {
        return cashBackAmount;
    }

    public DiscountUnit getCashBackDiscountUnit() {
        return cashBackDiscountUnit;
    }

    public Amount getInstantCashBackAmount() {
        return instantCashBackAmount;
    }

    public DiscountUnit getInstantDiscountUnit() {
        return instantDiscountUnit;
    }

    public Integer getEligibility() {
        return eligibility;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public void setCashBackAmount(Amount cashBackAmount)
    {
        this.cashBackAmount = cashBackAmount;
    }

    public void setCashBackDiscountUnit(DiscountUnit cashBackDiscountUnit)
    {
        this.cashBackDiscountUnit = cashBackDiscountUnit;
    }

    public void setInstantCashBackAmount(Amount instantCashBackAmount)
    {
        this.instantCashBackAmount = instantCashBackAmount;
    }

    public void setInstantDiscountUnit(DiscountUnit instantDiscountUnit)
    {
        this.instantDiscountUnit = instantDiscountUnit;
    }

    public void setEligibility(Integer eligibility)
    {
        this.eligibility = eligibility;
    }

    public void setTransactionAmount(Double transactionAmount)
    {
        this.transactionAmount = transactionAmount;
    }

    public void setReferenceId(String referenceId)
    {
        this.referenceId = referenceId;
    }

    public void setTermsConditions(String termsConditions)
    {
        this.termsConditions = termsConditions;
    }

    public Integer getOfferType()
    {
        return offerType;
    }

    public void setOfferType(Integer offerType)
    {
        this.offerType = offerType;
    }
}
