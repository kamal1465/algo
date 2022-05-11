package com.kamals.algo.ecommerce.model;

import java.math.BigDecimal;

public class CartEntry
{
    private int lineNumber;

    private Product product;

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal discount;

    private BigDecimal netPrice;

    private CartEntry masterEntry;
}
