package com.kamals.algo.idiom.proration;

import java.math.BigDecimal;
import java.util.Map;

public class OrderEntry
{
    private int lineNo;
    private Order order;
    private BigDecimal totalPrice;
    private BigDecimal basePrice;
    private Map<Promotion, BigDecimal> promotionDiscounts;

    public OrderEntry(int lineNo, Order order, BigDecimal basePrice, Map<Promotion, BigDecimal> promotionDiscounts)
    {
        this.lineNo = lineNo;
        this.order = order;
        this.basePrice = basePrice;
        this.promotionDiscounts = promotionDiscounts;
        this.totalPrice = basePrice;
        for (Map.Entry<Promotion, BigDecimal> entry : promotionDiscounts.entrySet())
        {
            totalPrice = totalPrice.subtract(entry.getValue());
        }
    }

    public int getLineNo()
    {
        return lineNo;
    }

    public Map<Promotion, BigDecimal> getPromotionDiscounts()
    {
        return promotionDiscounts;
    }

    public Order getOrder()
    {
        return order;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public BigDecimal getBasePrice()
    {
        return basePrice;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(lineNo)
                .append(". ")
                .append(basePrice)
                .append(" ");

        for (Map.Entry<Promotion, BigDecimal> entry : promotionDiscounts.entrySet())
        {
            stringBuilder.append(entry.getKey())
                    .append(entry.getValue());
        }

        stringBuilder.append(" Total=").append(totalPrice);

        return stringBuilder.toString();
    }
}
