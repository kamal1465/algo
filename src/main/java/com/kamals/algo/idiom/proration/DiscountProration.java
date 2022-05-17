package com.kamals.algo.idiom.proration;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DiscountProration
{
    Collection<Promotion> getOrderLevelPromotions(final Order order);

    Collection<Promotion> getEntryLevelPromotions(final Order order, final OrderEntry orderEntry);

    boolean isPromotionApplicableToEntry(final Promotion promotion, final OrderEntry orderEntry);

    BigDecimal getGlobalDiscountFromPromotion(final Order order, final Promotion promotion);

    BigDecimal getEntryDiscountFromPromotion(final OrderEntry orderEntry, final Promotion promotion);

    BigDecimal getEffectiveEntryDiscountFromPromotion(final OrderEntry orderEntry, final Promotion promotion);

    Double getEffectivePrice(final OrderEntry orderEntry);

    Map<OrderEntry, BigDecimal> getEffectivePrices(final Order order);

    Map<OrderEntry, Map<Promotion, BigDecimal>> getAllPromotionsForEntry(Order order);
}
