package com.kamals.algo.idiom.proration;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class DiscountProrationImpl implements DiscountProration
{
    @Override
    public Collection<Promotion> getOrderLevelPromotions(Order order)
    {
        return order.getPromotionDiscounts().keySet().stream().filter(p -> CollectionUtils.isEmpty(p.getEntries())).collect(Collectors.toList());
    }

    @Override
    public Collection<Promotion> getEntryLevelPromotions(Order order, OrderEntry orderEntry)
    {
        Collection<Promotion> promotions = orderEntry.getPromotionDiscounts().keySet();
        promotions.addAll(order.getPromotionDiscounts().keySet().stream().filter(p -> p.getEntries().contains(orderEntry)).collect(Collectors.toList()));
        return promotions;
    }

    @Override
    public boolean isPromotionApplicableToEntry(Promotion promotion, OrderEntry orderEntry)
    {
        return orderEntry.getPromotionDiscounts().get(promotion) != null;
    }

    @Override
    public BigDecimal getEntryDiscountFromPromotion(OrderEntry orderEntry, Promotion promotion)
    {
        for (Map.Entry<Promotion, BigDecimal> promoEntry : orderEntry.getPromotionDiscounts().entrySet())
        {
            if (promoEntry.getKey().equals(promotion))
            {
                return promoEntry.getValue();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getGlobalDiscountFromPromotion(Order order, Promotion promotion)
    {
        for (Map.Entry<Promotion, BigDecimal> promoEntry : order.getPromotionDiscounts().entrySet())
        {
            if (promoEntry.getKey().equals(promotion))
            {
                return promoEntry.getValue();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getEffectiveEntryDiscountFromPromotion(final OrderEntry orderEntry, final Promotion promotion)
    {
        BigDecimal entryDiscount = BigDecimal.ZERO;
        if (isPromotionApplicableToEntry(promotion, orderEntry))
        {
            entryDiscount = getEntryDiscountFromPromotion(orderEntry, promotion);

            boolean reAdjust = false;
            BigDecimal totalBasePrice = new BigDecimal("0.0").setScale(2, RoundingMode.DOWN);
            BigDecimal totalDiscount = new BigDecimal("0.0").setScale(2, RoundingMode.DOWN);
            Order order = orderEntry.getOrder();
            boolean globalDiscountPresent = false;
            BigDecimal globalDiscount = getGlobalDiscountFromPromotion(order, promotion);
            if (globalDiscount.compareTo(BigDecimal.ZERO) > 0)
            {
                System.out.println("Found global discount = " + globalDiscount);
                globalDiscountPresent = true;
                if (promotion.getEntries().size() == 1)
                {
                    entryDiscount = entryDiscount.add(globalDiscount);
                }
                else
                {
                    reAdjust = true;
                    totalDiscount = globalDiscount;
                }
            }

            for (OrderEntry oe : promotion.getEntries())
            {
                BigDecimal entryPrice = globalDiscountPresent ? oe.getTotalPrice() : oe.getBasePrice();
                totalBasePrice = totalBasePrice.add(entryPrice);
                if (!globalDiscountPresent)
                {
                    BigDecimal discount = getEntryDiscountFromPromotion(oe, promotion);
                    totalDiscount = totalDiscount.add(discount);
                    if (oe.getTotalPrice().equals(BigDecimal.ZERO))
                    {
                        reAdjust = true;
                        System.out.println("Found zero price product with Promotion, readjusting price");
                    }
                }
            }
            if (reAdjust)
            {
                BigDecimal proratedDiscount = getProratedDiscount(orderEntry, promotion, totalBasePrice, totalDiscount, globalDiscountPresent);

                entryDiscount = globalDiscountPresent ? entryDiscount.add(proratedDiscount) : proratedDiscount;
            }
        }
        return entryDiscount;
    }

    private BigDecimal getProratedDiscount(OrderEntry orderEntry, Promotion promotion, BigDecimal totalBasePrice, BigDecimal totalDiscount, boolean isGlobal)
    {
        BigDecimal entryDiscount;
        System.out.println("Prorating Promotion discount " + totalDiscount + " over total " + totalBasePrice + " isCart=" + isGlobal);
        BigDecimal totalDiscountProrated = new BigDecimal("0.0").setScale(2, RoundingMode.DOWN);
        Map<OrderEntry, BigDecimal> entryDiscountMap = new LinkedHashMap<>();
        for (OrderEntry oe : promotion.getEntries())
        {
            double entryPrice = (isGlobal ? oe.getTotalPrice() : oe.getBasePrice()).doubleValue();
            double actualDiscount = totalDiscount.doubleValue() * entryPrice / totalBasePrice.doubleValue();
            BigDecimal entryDiscountBD = new BigDecimal(actualDiscount).setScale(2, RoundingMode.HALF_UP);
            entryDiscountMap.put(oe, entryDiscountBD);
            totalDiscountProrated = totalDiscountProrated.add(entryDiscountBD);
        }
        if (totalDiscount.compareTo(totalDiscountProrated) != 0)
        {
            BigDecimal delta = totalDiscount.subtract(totalDiscountProrated);
            BigDecimal c = delta.multiply(new BigDecimal("100.0")).abs();
            if (delta.compareTo(BigDecimal.ZERO) != 0 && c.compareTo(BigDecimal.ZERO) != 0)
            {
                delta = delta.divide(c, 2, RoundingMode.DOWN);
                for (Map.Entry<OrderEntry, BigDecimal> entry : entryDiscountMap.entrySet())
                {
                    if (c.compareTo(BigDecimal.ONE) >= 0 &&
                            entry.getValue().compareTo(BigDecimal.ZERO) > 0 &&
                            entry.getValue().compareTo(delta) > 0)
                    {
                        entry.setValue(entry.getValue().add(delta));
                        c = c.subtract(BigDecimal.ONE);
                    }
                    if (c.compareTo(BigDecimal.ONE) < 0)
                    {
                        break;
                    }
                }
            }
        }
        entryDiscount = entryDiscountMap.get(orderEntry);
        return entryDiscount;
    }

    @Override
    public Double getEffectivePrice(final OrderEntry orderEntry)
    {
        Map<OrderEntry, BigDecimal> effectivePriceMap = getEffectivePrices(orderEntry.getOrder());
        return effectivePriceMap.get(orderEntry).doubleValue();
    }

    @Override
    public Map<OrderEntry, BigDecimal> getEffectivePrices(Order order)
    {
        Map<OrderEntry, BigDecimal> effectivePricesMap = new LinkedHashMap<>();
        Map<OrderEntry, Map<Integer, BigDecimal>> orderLinePromotions = new LinkedHashMap<>();
        //List<Promotion> invoiceMsgPromotions=getInvoiceMsgPromotions(order);
        Collection<Promotion> globalPromotions = getOrderLevelPromotions(order);
        Map<Integer, BigDecimal> globalPromotionDiscount = new LinkedHashMap<>();
        Map<Integer, BigDecimal> globalPromotionProratedDiscount = new LinkedHashMap<>();
        int i = 1;
        for (final Promotion promotion : globalPromotions)
        {
            BigDecimal discount = getGlobalDiscountFromPromotion(order, promotion);
            globalPromotionDiscount.put(i, discount);
            globalPromotionProratedDiscount.put(i, new BigDecimal("0.0").setScale(2, RoundingMode.DOWN));
            i++;
        }

        BigDecimal orderSubTotal = new BigDecimal("0.0").setScale(2, RoundingMode.DOWN);

        for (OrderEntry orderEntry : order.getEntries())
        {
            BigDecimal effectivePrice = orderEntry.getBasePrice().setScale(2, RoundingMode.DOWN);
            //adding invoice message promotions if any
            Collection<Promotion> entryPromotions = getEntryLevelPromotions(order, orderEntry);
            //List<Promotion> entryPromotions = getEntryLevelPromotions(order, orderEntry,invoiceMsgPromotions);

            for (Promotion promotion : entryPromotions)
            {
                BigDecimal actualDiscount = getEffectiveEntryDiscountFromPromotion(orderEntry, promotion);

                System.out.println("Actual discount = " + actualDiscount);
                effectivePrice = effectivePrice.subtract(actualDiscount);
            }
            orderSubTotal = orderSubTotal.add(effectivePrice);
            effectivePricesMap.put(orderEntry, effectivePrice);
        }
        System.out.println("Order subtotal = " + orderSubTotal);
        for (OrderEntry orderEntry : order.getEntries())
        {
            BigDecimal effectivePrice = effectivePricesMap.get(orderEntry);
            Map<Integer, BigDecimal> promotionMap = new LinkedHashMap<>();
            double entryPriceRatio = effectivePrice.doubleValue() / orderSubTotal.doubleValue();
            for (final Map.Entry<Integer, BigDecimal> entry : globalPromotionDiscount.entrySet())
            {
                double proratedDiscount = entry.getValue().doubleValue() * entryPriceRatio;
                BigDecimal proratedDiscountBD = new BigDecimal(proratedDiscount).setScale(2, RoundingMode.HALF_UP);

                System.out.println("Prorated Order discount = " + proratedDiscountBD);
                promotionMap.put(entry.getKey(), proratedDiscountBD);
                effectivePrice = effectivePrice.subtract(proratedDiscountBD);
                globalPromotionProratedDiscount.merge(entry.getKey(), proratedDiscountBD, BigDecimal::add);
            }
            effectivePricesMap.put(orderEntry, effectivePrice);
            orderLinePromotions.put(orderEntry, promotionMap);
        }

        Map<Integer, BigDecimal> globalPromotionDeltaDiscount = new LinkedHashMap<>();
        for (final Map.Entry<Integer, BigDecimal> entry : globalPromotionDiscount.entrySet())
        {
            globalPromotionDeltaDiscount.put(entry.getKey(), entry.getValue().subtract(globalPromotionProratedDiscount.get(entry.getKey())));
        }

        Iterator<Map.Entry<OrderEntry, Map<Integer, BigDecimal>>> iter = orderLinePromotions.entrySet().iterator();
        for (final Map.Entry<Integer, BigDecimal> entry : globalPromotionDeltaDiscount.entrySet())
        {
            Integer j = entry.getKey();
            BigDecimal delta = entry.getValue();
            if (delta.compareTo(BigDecimal.ZERO) > 0)
            {
                BigDecimal c = delta.multiply(new BigDecimal("100.0"));
                delta = delta.divide(c, 2, RoundingMode.DOWN);

                while (c.compareTo(BigDecimal.ONE) >= 0)
                {
                    if (!iter.hasNext())
                    {
                        iter = orderLinePromotions.entrySet().iterator();
                    }
                    Map.Entry<OrderEntry, Map<Integer, BigDecimal>> entryPromo = iter.next();
                    OrderEntry orderEntry = entryPromo.getKey();
                    Map<Integer, BigDecimal> promoMap = entryPromo.getValue();
                    BigDecimal discount = promoMap.get(j);
                    if (discount.compareTo(BigDecimal.ZERO) > 0 && discount.compareTo(delta) > 0)
                    {
                        promoMap.put(j, discount.add(delta));
                        effectivePricesMap.put(orderEntry, effectivePricesMap.get(orderEntry).subtract(delta));
                        c = c.subtract(BigDecimal.ONE);
                    }
                }
            }
        }
        iter = orderLinePromotions.entrySet().iterator();
        for (final Map.Entry<Integer, BigDecimal> entry : globalPromotionDeltaDiscount.entrySet())
        {
            Integer j = entry.getKey();
            BigDecimal delta = entry.getValue();
            if (delta.compareTo(BigDecimal.ZERO) < 0)
            {
                delta = delta.negate();
                BigDecimal c = delta.multiply(new BigDecimal("100.0"));
                delta = delta.divide(c, 2, RoundingMode.DOWN);

                while (c.compareTo(BigDecimal.ONE) >= 0)
                {
                    if (!iter.hasNext())
                    {
                        iter = orderLinePromotions.entrySet().iterator();
                    }
                    Map.Entry<OrderEntry, Map<Integer, BigDecimal>> entryPromo = iter.next();
                    OrderEntry orderEntry = entryPromo.getKey();
                    Map<Integer, BigDecimal> promoMap = entryPromo.getValue();
                    BigDecimal discount = promoMap.get(j);
                    if (discount.compareTo(BigDecimal.ZERO) > 0 && discount.compareTo(delta) > 0)
                    {
                        promoMap.put(j, discount.subtract(delta));
                        effectivePricesMap.put(orderEntry, effectivePricesMap.get(orderEntry).add(delta));
                        c = c.subtract(BigDecimal.ONE);
                    }
                }
            }
        }
        return effectivePricesMap;
    }

    public Map<OrderEntry, Map<Promotion, BigDecimal>> getAllPromotionsForEntry(Order order)
    {
        Map<OrderEntry, Map<Promotion, BigDecimal>> orderLinePromotions = new LinkedHashMap<>();
        Map<OrderEntry, BigDecimal> effectivePriceMap = new HashMap<>();
        Collection<Promotion> globalPromotions = getOrderLevelPromotions(order);
        Map<Promotion, BigDecimal> globalPromotionDiscount = new LinkedHashMap<>();
        Map<Promotion, BigDecimal> globalPromotionProratedDiscount = new LinkedHashMap<>();

        for (final Promotion Promotion : globalPromotions)
        {
            BigDecimal discount = getGlobalDiscountFromPromotion(order, Promotion);
            globalPromotionDiscount.put(Promotion, discount);
            globalPromotionProratedDiscount.put(Promotion, new BigDecimal("0.0").setScale(2, RoundingMode.DOWN));
        }

        BigDecimal orderSubTotal = new BigDecimal("0.0").setScale(2, RoundingMode.DOWN);
        for (OrderEntry orderEntry : order.getEntries())
        {
            Map<Promotion, BigDecimal> promotionMap = new LinkedHashMap<>();

            BigDecimal effectivePrice = orderEntry.getBasePrice().setScale(2, RoundingMode.DOWN);
            //include invoice message changes here --CHANGED--
            //List<Promotion> entryPromotions = getEntryLevelPromotions(order, orderEntry, invoiceMsgPromotions);
            Collection<Promotion> entryPromotions = getEntryLevelPromotions(order, orderEntry);

            for (Promotion promotion : entryPromotions)
            {
                BigDecimal actualDiscount = getEffectiveEntryDiscountFromPromotion(orderEntry, promotion);

                System.out.println("Actual discount = " + actualDiscount + "for promotion : " + promotion.getCode());
                effectivePrice = effectivePrice.subtract(actualDiscount);
                promotionMap.put(promotion, actualDiscount);
            }
            orderSubTotal = orderSubTotal.add(effectivePrice);
            orderLinePromotions.put(orderEntry, promotionMap);
            effectivePriceMap.put(orderEntry, effectivePrice);
        }
        System.out.println("Order subtotal = " + orderSubTotal);
        for (OrderEntry orderEntry : order.getEntries())
        {
            Map<Promotion, BigDecimal> promotionMap = orderLinePromotions.get(orderEntry);
            BigDecimal effectivePrice = effectivePriceMap.get(orderEntry);
            double entryPriceRatio = effectivePrice.doubleValue() / orderSubTotal.doubleValue();
            for (final Map.Entry<Promotion, BigDecimal> entry : globalPromotionDiscount.entrySet())
            {
                double proratedDiscount = entry.getValue().doubleValue() * entryPriceRatio;
                BigDecimal proratedDiscountBD = new BigDecimal(proratedDiscount).setScale(2, RoundingMode.HALF_UP);

                System.out.println("Prorated Order discount = " + proratedDiscountBD);
                promotionMap.put(entry.getKey(), proratedDiscountBD);

                globalPromotionProratedDiscount.merge(entry.getKey(), proratedDiscountBD, BigDecimal::add);
            }
        }

        Map<Promotion, BigDecimal> globalPromotionDeltaDiscount = new LinkedHashMap<>();
        for (final Map.Entry<Promotion, BigDecimal> entry : globalPromotionDiscount.entrySet())
        {
            globalPromotionDeltaDiscount.put(entry.getKey(), entry.getValue().subtract(globalPromotionProratedDiscount.get(entry.getKey())));
        }

        Iterator<Map.Entry<OrderEntry, Map<Promotion, BigDecimal>>> iter = orderLinePromotions.entrySet().iterator();
        for (final Map.Entry<Promotion, BigDecimal> entry : globalPromotionDeltaDiscount.entrySet())
        {
            Promotion promotion = entry.getKey();
            BigDecimal delta = entry.getValue();
            if (delta.compareTo(BigDecimal.ZERO) > 0)
            {
                BigDecimal c = delta.multiply(new BigDecimal("100.0"));
                delta = delta.divide(c, 2, RoundingMode.DOWN);

                while (c.compareTo(BigDecimal.ONE) >= 0)
                {
                    if (!iter.hasNext())
                    {
                        iter = orderLinePromotions.entrySet().iterator();
                    }
                    Map.Entry<OrderEntry, Map<Promotion, BigDecimal>> entryPromo = iter.next();

                    Map<Promotion, BigDecimal> promoMap = entryPromo.getValue();
                    BigDecimal discount = promoMap.get(promotion);
                    if (discount.compareTo(BigDecimal.ZERO) > 0 && discount.compareTo(delta) > 0)
                    {
                        promoMap.put(promotion, discount.add(delta));
                        c = c.subtract(BigDecimal.ONE);
                    }
                }
            }
        }
        iter = orderLinePromotions.entrySet().iterator();
        for (final Map.Entry<Promotion, BigDecimal> entry : globalPromotionDeltaDiscount.entrySet())
        {
            Promotion promotion = entry.getKey();
            BigDecimal delta = entry.getValue();
            if (delta.compareTo(BigDecimal.ZERO) < 0)
            {
                delta = delta.negate();
                BigDecimal c = delta.multiply(new BigDecimal("100.0"));
                delta = delta.divide(c, 2, RoundingMode.DOWN);

                while (c.compareTo(BigDecimal.ONE) >= 0)
                {
                    if (!iter.hasNext())
                    {
                        iter = orderLinePromotions.entrySet().iterator();
                    }
                    Map.Entry<OrderEntry, Map<Promotion, BigDecimal>> entryPromo = iter.next();

                    Map<Promotion, BigDecimal> promoMap = entryPromo.getValue();
                    BigDecimal discount = promoMap.get(promotion);
                    if (discount.compareTo(BigDecimal.ZERO) > 0 && discount.compareTo(delta) > 0)
                    {
                        promoMap.put(promotion, discount.subtract(delta));
                        c = c.subtract(BigDecimal.ONE);
                    }
                }
            }
        }
        return orderLinePromotions;
    }

}
