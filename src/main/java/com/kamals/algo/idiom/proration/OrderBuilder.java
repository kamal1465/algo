package com.kamals.algo.idiom.proration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderBuilder
{
    private int lineNo = 0;
    private String orderId;
    private Order order;
    private List<OrderEntry> entries = new ArrayList<>();
    private Map<Promotion, BigDecimal> promoEntries = new LinkedHashMap<>();

    public OrderBuilder(String orderId)
    {
        this.orderId = orderId;
    }

    public void addEntry(double basePrice, Object... promo)
    {
        Map<Promotion, BigDecimal> promotionDiscounts = new LinkedHashMap<>();
        for (int i = 0; i < promo.length / 2; i++)
        {
            promotionDiscounts.put(new Promotion((String) promo[i], null),
                    BigDecimal.valueOf((Double) promo[i + 1]));

        }
        OrderEntry orderEntry = new OrderEntry(++lineNo, order, BigDecimal.valueOf(basePrice), promotionDiscounts);
        entries.add(orderEntry);
    }

    public void addPromo(String promoCode, double promoDiscount)
    {
        promoEntries.put(new Promotion(promoCode, null), BigDecimal.valueOf(promoDiscount));
    }

    public Order buildOrder()
    {
        order = new Order(orderId, entries, promoEntries);
        return order;
    }
}
