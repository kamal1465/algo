package com.kamals.algo.idiom.proration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Order
{
    private String code;
    private List<OrderEntry> entries;
    private Map<Promotion, BigDecimal> promotionDiscounts;
    private BigDecimal totalPrice;

    public Order(String code, List<OrderEntry> entries, Map<Promotion, BigDecimal> promotionDiscounts)
    {
        this.code = code;
        this.entries = entries;
        this.promotionDiscounts = promotionDiscounts;
        this.totalPrice = BigDecimal.ZERO;
        for (OrderEntry entry : entries)
        {
            totalPrice = totalPrice.add(entry.getTotalPrice());
        }
        for (Map.Entry<Promotion, BigDecimal> entry : promotionDiscounts.entrySet())
        {
            totalPrice = totalPrice.subtract(entry.getValue());
        }
    }

    public String getCode()
    {
        return code;
    }

    public Map<Promotion, BigDecimal> getPromotionDiscounts()
    {
        return promotionDiscounts;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public List<OrderEntry> getEntries()
    {
        return entries;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order: ")
                .append(code)
                .append('\n');
        for (OrderEntry entry : entries)
        {
            stringBuilder.append(entry)
                    .append('\n');
        }
        for (Map.Entry<Promotion, BigDecimal> entry : promotionDiscounts.entrySet())
        {
            stringBuilder.append(entry.getKey())
                    .append(entry.getValue())
                    .append('\n');
        }
        stringBuilder.append("Total=").append(totalPrice);

        return stringBuilder.toString();
    }
}
