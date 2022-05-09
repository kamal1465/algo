package com.kamals.algo.example.json;

public class Test
{
    public static void main(String[] args)
    {
        String json = "{\"idempotentKey\":\"a17212b4-6402-4679-aea8-50277bec8542\",\"initiatingEntityTimestamp\":\"2021-07-20T13:48:23.447Z\",\"selectionCriteria\":{\"merchantId\":\"100001000232907\",\"product\":[{\"productId\":\"491282716\",\"transactionAmount\":15000.0}]},\"offers\":[{\"code\":\"CB5\",\"name\":\"5 % Cashback with Credit Card\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"EMIHDFC5\",\"name\":\"Discounts based on EMI tenure 5 % cashback HDFC credit card - 3 months\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"REFINSTANT10\",\"name\":\"10% Instant Discount on all Refrigerators except Whirlpool with ICICI Platinum Credit Cards\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"FLATINSTANT10\",\"name\":\"Flat Discount on Cart Value\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"EMIAXIS5\",\"name\":\"Discounts based on EMI tenure-for category 5 % cashback Axis credit card - 3 months tenure\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"CART15\",\"name\":\"15 % Instant Discount with Axis Credit Cards\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"INSTANT500\",\"name\":\"Flat 500 Instant Discount with ICICI Bank Debit Cards\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"},{\"code\":\"ELINSTANT10\",\"name\":\"10% Instant Discount on Samsung Refrigerators and Mobile Phones with HDFC Credit Cards\",\"status\":1,\"eligibility\":2,\"logo\":\"OFFER LOGO\"}],\"product\":[{\"productId\":\"491282716\",\"offer\":[{\"code\":\"INSTANT500\",\"name\":\"Flat 500 Instant Discount with ICICI Bank Debit Cards\",\"description\":\"OFFER DESCRIPTION\",\"status\":1,\"eligibility\":1,\"offerType\":1,\"instantDiscountAmount\":{\"amount\":500.0},\"instantDiscountUnit\":{\"type\":1,\"value\":\"500\"},\"termsConditions\":\"TnC\",\"logo\":\"OFFER LOGO\"}]}]}";
        System.out.println(json);
        OfferResponse offerResponse = OfferResponse.fromString(json, OfferResponse.class);
        System.out.println(offerResponse);
    }
}
