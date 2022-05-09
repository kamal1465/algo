package com.kamals.algo.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundingTest
{

    public static void main(String[] args)
    {
        getEffectivePrice();
    }

    private static void getEffectivePrice()
    {
        OrderEntry orderEntry1 = new OrderEntry(200d, 0d);
        OrderEntry orderEntry2 = new OrderEntry(100d, 100d);

        Order order = new Order(Arrays.asList(orderEntry1, orderEntry2), Arrays.asList(25.0, 15.0));

        double totalBasePrice = 0.0;
        double totalDiscount = 0.0;

        for (OrderEntry orderEntry : order.getEntries())
        {
            totalBasePrice += orderEntry.getBasePrice();
            totalDiscount += orderEntry.getDiscount();
        }

        for (OrderEntry orderEntry : order.getEntries())
        {
            BigDecimal effectivePrice = new BigDecimal(orderEntry.getTotalPrice());

            double entryDiscount = orderEntry.getDiscount();
            double actualDiscount = orderEntry.getBasePrice() * totalDiscount / totalBasePrice;
            if (actualDiscount != entryDiscount)
            {
                System.out.println("Entry discount = " + entryDiscount + " Actual discount = " + actualDiscount);
                BigDecimal entryDiscountBD = new BigDecimal(entryDiscount)
                        .setScale(2, RoundingMode.HALF_UP);
                BigDecimal actualDiscountBD = new BigDecimal(actualDiscount)
                        .setScale(2, RoundingMode.HALF_UP);
                effectivePrice = effectivePrice.add(entryDiscountBD).subtract(actualDiscountBD);
                System.out.println(effectivePrice.doubleValue());
            }


            double totalOrderDiscount = 0.0;
            for (double discountValue : order.getDiscounts())
            {
                totalOrderDiscount += discountValue;
            }
            double discountRatio = totalOrderDiscount / order.getSubTotal();
            BigDecimal proratedDiscountBD = new BigDecimal(discountRatio).multiply(effectivePrice)
                    .setScale(2, RoundingMode.HALF_UP);
            effectivePrice = effectivePrice.subtract(proratedDiscountBD);

            orderEntry.setEffectivePrice(effectivePrice.doubleValue());
        }

        System.out.println(order);
    }

    static class Order
    {
        List<OrderEntry> entries;
        double totalPrice, subTotal;
        List<Double> discounts;

        Order(List<OrderEntry> entries, List<Double> discounts)
        {
            this.entries = entries;
            this.discounts = discounts;
            for (OrderEntry entry : entries)
            {
                this.subTotal += entry.getTotalPrice();
            }
            this.totalPrice = this.subTotal;
            for (double discount : discounts)
            {
                this.totalPrice -= discount;
            }
        }

        List<OrderEntry> getEntries()
        {
            if (entries == null)
            {
                entries = new ArrayList<>();
            }
            return entries;
        }

        public List<Double> getDiscounts()
        {
            return discounts;
        }

        double getTotalPrice()
        {
            return totalPrice;
        }

        double getSubTotal()
        {
            return subTotal;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            for (OrderEntry orderEntry : this.getEntries())
            {
                sb.append(orderEntry.toString());
            }
            sb.append("SubTotal=").append(subTotal).append(" Discount=").append(discounts) .append(" Total=").append(totalPrice);
            return sb.toString();
        }
    }

    static class OrderEntry
    {
        double basePrice, discount, totalPrice, effectivePrice;

        OrderEntry(double basePrice, double discount)
        {
            this.basePrice = basePrice;
            this.discount = discount;
            this.totalPrice = basePrice - discount;
        }

        double getBasePrice()
        {
            return basePrice;
        }

        double getDiscount()
        {
            return discount;
        }

        double getTotalPrice()
        {
            return totalPrice;
        }

        public double getEffectivePrice()
        {
            return effectivePrice;
        }

        public void setEffectivePrice(double effectivePrice)
        {
            this.effectivePrice = effectivePrice;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("BasePrice=").append(basePrice).append(" Discount=").append(discount) .append(" Total=").append(totalPrice).append(" EffectivePrice=").append(effectivePrice).append("\n");
            return sb.toString();
        }
    }
}
