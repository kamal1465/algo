package com.kamals.algo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TestCurrency {

    public static void main(String[] args) {
        double d = 823.9d;
        BigDecimal bd = new BigDecimal(d);//.setScale(2, RoundingMode.HALF_UP);
        System.out.println(d);
        System.out.println(bd);

        Map<String, Double> priceMap = new LinkedHashMap<>();
        List<Double> a = Arrays.asList(9990.0, 3699.0, 4499.0);
        for (int i = 0; i < a.size(); i++)
            priceMap.put(String.valueOf(i), a.get(i));
        double d1 = 1500.0;

        List<Double> b = Arrays.asList(10490.0, 22999.0, 10490.0);
        double d2 = 4197.9;
        System.out.println(priceMap);
        prorate(priceMap, d1);
        System.out.println(priceMap);
    }

    public static void prorate(List<Double> a, double d) {
        List<BigDecimal> pNew = new ArrayList<>();
        List<BigDecimal> pVal = new ArrayList<>();
        List<Double> ds = new ArrayList<>();
        double total = a.stream().reduce(0D, (x, y) -> x + y);
        System.out.println("Total = " + total);

        for (double x : a) {
            double y = d * x / total;
            ds.add(y);
            BigDecimal bd1 = new BigDecimal(y).setScale(2, RoundingMode.HALF_UP);
            BigDecimal bd2 = BigDecimal.valueOf(y).setScale(2, RoundingMode.HALF_UP);
            pNew.add(bd1);
            pVal.add(bd2);
        }

        System.out.println("Doubles = " + ds);
        System.out.println("BigDecimals (new) = " + pNew);
        System.out.println("BigDecimal (valueOf) = " + pVal);
    }

    public static void prorate(Map<String, Double> priceMap, Double planned) {
        double total = priceMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double diff = total - planned;

        if (diff == 0D) {
            return;
        }

        BigDecimal totalProrated = new BigDecimal("0.0").setScale(2, RoundingMode.DOWN);
        for (Map.Entry<String, Double> entry : priceMap.entrySet()) {
            double prorated = planned * entry.getValue() / total;
            BigDecimal proratedDecimal = new BigDecimal(prorated).setScale(2, RoundingMode.HALF_UP);
            entry.setValue(proratedDecimal.doubleValue());
            totalProrated = totalProrated.add(proratedDecimal);
        }
        if (totalProrated.compareTo(new BigDecimal(planned)) != 0) {
            BigDecimal delta = (new BigDecimal(planned)).subtract(totalProrated);
            BigDecimal c = delta.multiply(new BigDecimal("100.0")).abs();
            if (delta.compareTo(BigDecimal.ZERO) != 0 && c.compareTo(BigDecimal.ZERO) != 0) {
                delta = delta.divide(c, 2, RoundingMode.DOWN);
                for (Map.Entry<String, Double> entry : priceMap.entrySet()) {
                    BigDecimal entryValue = new BigDecimal(entry.getValue());
                    if (c.compareTo(BigDecimal.ONE) >= 0 &&
                            entryValue.compareTo(BigDecimal.ZERO) > 0 &&
                            entryValue.compareTo(delta) > 0) {
                        entry.setValue(entryValue.add(delta).doubleValue());
                        c = c.subtract(BigDecimal.ONE);
                    }
                    if (c.compareTo(BigDecimal.ONE) < 0) {
                        break;
                    }
                }

            }
        }

    }

    private static void test23()
    {
        TreeSet<Integer> x = new TreeSet<>();

//        x.ceilin()
    }
}
