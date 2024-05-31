package com.kamals.algo.example;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class StringTest
{
    public static void main(String[] args)
    {
        List<Integer> ml1 = new ArrayList<>();
//        System.out.println("*");
//        System.out.println(ml1.stream().map(e -> e.toString()).collect(Collectors.joining("")));
//        System.out.println("*");
        //char c = 192;
        //System.out.println(c);
        //testStringTokenize("a,b,c ");
        //testSubstringBetween();

        //testSplit();

        //String s = "{\"firstname\"\t: \"Abc\", \"lastname\"\t: \"Xyz\",  \"dob\"\t\t\t: \"25-12-1994\", \"gender\"\t\t: \"M\", \n\"mobileno\"\t: \"9090909090\", \n\"email\"\t\t: \"abc.xyz@abc.com\", \n\"pincode\"\t\t: \"560010\", \n\"city\"\t\t: \"Bangalore\", \n\"store\"\t\t: \"5917\",\n\"tnc\"\t\t\t: true\n}";

        //String s = "{\"message\":\"Success\",\"data\":[{\"id\":2873563,\"client_name\":\"JioMar_1P_RFC\",\"client_order_id\":\"NIT81782788990WW-07\",\"awb_number\":\"SF100000022431JIF\",\"product_value\":0.0,\"cod_amount\":0,\"payment_mode\":\"prepaid\",\"order_date\":\"2024-04-05\",\"promised_delivery_date\":null,\"status_display\":\"Sent To Forward\",\"status\":\"sent_to_fwd\",\"pickup_details\":{\"name\":\"\",\"contact\":\"\",\"address_line_1\":\"\",\"address_line_2\":\"\",\"city\":\"Tumkur\",\"state\":\"KAR\",\"pincode\":562123,\"alternate_contact\":\"\",\"sms_contact\":\"\",\"latitude\":null,\"longitude\":null},\"delivery_details\":{\"name\":\"\",\"contact\":\"\",\"address_line_1\":\"\",\"address_line_2\":\"\",\"city\":\"RAICHUR\",\"state\":\"KAR\",\"pincode\":560077,\"alternate_contact\":\"\",\"sms_contact\":\"\",\"latitude\":null,\"longitude\":null},\"product_details\":[{\"sku_id\":\"491661272\",\"sku_name\":\"\",\"price\":\"\",\"return_reason\":null,\"category\":\"\",\"brand\":null,\"additional_details\":\"{\\\"requires_extra_care\\\": false, \\\"type_extra_care\\\": null, \\\"size\\\": 0, \\\"quantity\\\": 2}\",\"seller_name\":\"A02C\",\"seller_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"seller_state\":\"KAR\",\"hsn_code\":\"33049990\",\"invoice_no\":null,\"sgst_amount\":0.0,\"cgst_amount\":0.0,\"igst_amount\":0.0,\"gstin_number\":null,\"total_tax_value\":0.0,\"qc_required\":true,\"qc_rules\":\"\"},{\"sku_id\":\"491661273\",\"sku_name\":\"\",\"price\":\"\",\"return_reason\":null,\"category\":\"\",\"brand\":null,\"additional_details\":\"{\\\"requires_extra_care\\\": false, \\\"type_extra_care\\\": null, \\\"size\\\": 0, \\\"quantity\\\": 2}\",\"seller_name\":\"A02C\",\"seller_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"seller_state\":\"KAR\",\"hsn_code\":\"33049991\",\"invoice_no\":null,\"sgst_amount\":0.0,\"cgst_amount\":0.0,\"igst_amount\":0.0,\"gstin_number\":null,\"total_tax_value\":0.0,\"qc_required\":true,\"qc_rules\":\"\"}],\"tracking_details\":[{\"created\":\"2024-04-05T10:29:08Z\",\"location\":\"NCR Bijwasan DC\",\"status_id\":\"new\",\"status\":\"New\",\"remarks\":\"Item New at NCR Bijwasan DC\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T07:28:01Z\",\"location\":\"NCR Bijwasan DC\",\"status_id\":\"recd_at_fwd_dc\",\"status\":\"Received at Forward DC\",\"remarks\":\"Item Received at dc at NCR Bijwasan DC\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T07:28:21Z\",\"location\":\"NCR Bijwasan DC\",\"status_id\":\"item_manifested\",\"status\":\"Item added to Bag\",\"remarks\":\"Item added to bag at NCR Bijwasan DC\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T07:28:55Z\",\"location\":\"Bangalore DC\",\"status_id\":\"bag_received_at_via\",\"status\":\"Bag Received at Via\",\"remarks\":\"Bag received at via Bangalore DC\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T07:30:04Z\",\"location\":\"Bangalore DC\",\"status_id\":\"bag_in_transit\",\"status\":\"Bag In Transit\",\"remarks\":\"Bag in transit from Bangalore DC\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T08:16:41Z\",\"location\":\"BLR_Hebbal\",\"status_id\":\"recd_at_fwd_hub\",\"status\":\"Received at Forward Hub\",\"remarks\":\"Item Received at hub at BLR_Hebbal\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T08:17:18Z\",\"location\":\"BLR_Hebbal\",\"status_id\":\"assigned_for_delivery\",\"status\":\"Assigned For Delivery\",\"remarks\":\"Item Assigned at BLR_Hebbal\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T09:34:38Z\",\"location\":\"BLR_Hebbal\",\"status_id\":\"ofd\",\"status\":\"Out For Delivery\",\"remarks\":\"Item OFD at BLR_Hebbal\",\"awb_number\":\"SF100000022431JIF\"},{\"created\":\"2024-04-08T09:35:40Z\",\"location\":\"BLR_Hebbal\",\"status_id\":\"delivered\",\"status\":\"Delivered\",\"remarks\":\"Item Delivered at BLR_Hebbal\",\"awb_number\":\"SF100000022431JIF\"}]}]}";
        //String s = "{\"client_order_number\":\"NIT88717311W\",\"client_request_id\":\"SF100000192431JIF\",\"warehouse_name\":\"A02C\",\"warehouse_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"destination_pincode\":562123,\"unique_code\":\"S402\",\"total_amount\":0.0,\"price\":0.0,\"pickup_type\":\"Regular\",\"address_attributes\":{\"address_line\":\"845, Post Offices, RAICHUR, KARNATAKA PINCode, Search, Post Office Details, All India Post\",\"city\":\"RAICHUR\",\"country\":\"IN\",\"pincode\":560077,\"name\":\"Tanmay\",\"phone_number\":\"9304965123\",\"sms_contact\":\"9304965123\"},\"seller_attributes\":{\"name\":\"S402\",\"address_line\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"city\":\"Tumkur\",\"pincode\":562123,\"phone\":\"9999999999\",\"unique_code\":\"S402\"},\"skus_attributes\":[{\"name\":\"Macbook charger\",\"client_sku_id\":\"491661272\",\"price\":1000.0,\"brand\":\"Brand of the product\",\"category\":\"Laptop\",\"return_reason\":\"Damaged Product\",\"qc_required\":\"false\",\"seller_details\":{\"regd_name\":\"A02C\",\"regd_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"state\":\"KAR\"},\"taxes\":{\"cgst_amount\":0.0,\"sgst_amount\":0.0,\"igst_amount\":0.0,\"total_tax_amount\":0.0},\"additional_details\":{\"requires_extra_care\":\"false\",\"type_extra_care\":\"Laptop\",\"size\":0,\"quantity\":2}},{\"name\":\"Macbook charger1\",\"client_sku_id\":\"491661273\",\"price\":1000.0,\"brand\":\"Brand of the product\",\"category\":\"Laptop1\",\"return_reason\":\"Damaged Product\",\"qc_required\":\"false\",\"seller_details\":{\"regd_name\":\"A02C\",\"regd_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"state\":\"KAR\"},\"taxes\":{\"cgst_amount\":0.0,\"sgst_amount\":0.0,\"igst_amount\":0.0,\"total_tax_amount\":0.0},\"additional_details\":{\"requires_extra_care\":\"false\",\"type_extra_care\":\"Laptop1\",\"size\":0,\"quantity\":2}}],\"hsn_code\":\"33049991\",\"rts_details\":{\"name\":\"A02C\",\"contact_no\":\"9999999999\",\"address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"city\":\"Tumkur\",\"state\":\"KAR\",\"pincode\":562123,\"email\":\"shipper@reliance.com\"}}";
//        String s = "{\"R107620440JIF\":[{\"client_order_number\":\"NIT88771623W\",\"request_type\":\"pickup\",\"client_request_id\":\"R107620440JIF\",\"client_id\":2772,\"destination_pincode\":null,\"price\":null,\"address\":{\"name\":\"\",\"phone_number\":\"\",\"address_line\":\"\",\"city\":\"RAICHUR\",\"state\":null,\"pincode\":560077,\"created_at\":\"2024-04-15T06:55:26.000000+0000\",\"updated_at\":\"2024-04-15T06:55:26.000000+0000\",\"alternate_contact\":\"\"},\"skus\":[{\"client_sku_id\":\"491661272\",\"name\":\"\",\"price\":null,\"return_reason\":\"Damaged Product\",\"brand\":\"Brand of the product\",\"category\":\"Laptop\",\"additional_details\":{\"requires_extra_care\":false,\"type_extra_care\":null,\"size\":0,\"quantity\":2},\"picked\":\"picked\",\"qc_status\":null,\"qc_remarks\":\"\",\"seller_details\":{\"regd_name\":\"A02C\",\"regd_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"state\":\"KAR\",\"gstin\":null},\"taxes\":{\"cgst_amount\":0.0,\"sgst_amount\":0.0,\"igst_amount\":0.0,\"total_tax_amount\":0.0},\"hsn_code\":null,\"invoice_no\":null,\"qc_images\":[]},{\"client_sku_id\":\"491661273\",\"name\":\"\",\"price\":null,\"return_reason\":\"Damaged Product\",\"brand\":\"Brand of the product\",\"category\":\"Laptop1\",\"additional_details\":{\"requires_extra_care\":false,\"type_extra_care\":null,\"size\":0,\"quantity\":2},\"picked\":\"picked\",\"qc_status\":null,\"qc_remarks\":\"\",\"seller_details\":{\"regd_name\":\"A02C\",\"regd_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"state\":\"KAR\",\"gstin\":null},\"taxes\":{\"cgst_amount\":0.0,\"sgst_amount\":0.0,\"igst_amount\":0.0,\"total_tax_amount\":0.0},\"hsn_code\":null,\"invoice_no\":null,\"qc_images\":[]}],\"seller\":{\"name\":\"\",\"phone_number\":\"\",\"address_line\":\"\",\"city\":\"Tumkur\",\"state\":null,\"pincode\":562123,\"created_at\":\"2024-04-15T06:55:26.000000+0000\",\"updated_at\":\"2024-04-15T06:55:26.000000+0000\",\"alternate_contact\":\"\"},\"status_last_updated_at\":\"2024-04-15T13:01:03Z\",\"pickup_request_state_histories\":[{\"created_at\":\"2024-04-15T06:55:26Z\",\"current_location\":\"BLR_Hebbal\",\"state\":\"New\",\"comment\":\"Item New at BLR_Hebbal\"},{\"created_at\":\"2024-04-15T08:27:05Z\",\"current_location\":\"BLR_Hebbal\",\"state\":\"Assigned\",\"comment\":\"Item Assigned For Customer Pickup at BLR_Hebbal\"},{\"created_at\":\"2024-04-15T08:42:31Z\",\"current_location\":\"BLR_Hebbal\",\"state\":\"Out For Pickup\",\"comment\":\"Item Out For Pickup at BLR_Hebbal\"},{\"created_at\":\"2024-04-15T08:52:46Z\",\"current_location\":\"BLR_Hebbal\",\"state\":\"Received\",\"comment\":\"Received at hub Successfully\"},{\"created_at\":\"2024-04-15T10:58:54Z\",\"current_location\":\"BLR_Hebbal\",\"state\":\"Item added to Bag\",\"comment\":\"Item added to bag at BLR_Hebbal\"},{\"created_at\":\"2024-04-15T11:00:00Z\",\"current_location\":\"Bangalore DC\",\"state\":\"Bag Received\",\"comment\":\"Bag received at Bangalore DC\"},{\"created_at\":\"2024-04-15T11:00:23Z\",\"current_location\":\"Bangalore DC\",\"state\":\"Received at Return DC\",\"comment\":\"Item Received at RTS DC at Bangalore DC\"},{\"created_at\":\"2024-04-15T12:39:55Z\",\"current_location\":\"Bangalore DC\",\"state\":\"Return to Seller initiated\",\"comment\":\"Item return to seller initiated at Bangalore DC\"},{\"created_at\":\"2024-04-15T12:57:00Z\",\"current_location\":\"BLR_NandiniLayout\",\"state\":\"Received at RTS destination hub\",\"comment\":\"Item received at RTS destination hub BLR_NandiniLayout\"},{\"created_at\":\"2024-04-15T13:00:26Z\",\"current_location\":\"BLR_NandiniLayout\",\"state\":\"Return Shipment Out for Delivery\",\"comment\":\"Item out for delivery to seller at BLR_NandiniLayout\"},{\"created_at\":\"2024-04-15T13:01:02Z\",\"current_location\":\"BLR_NandiniLayout\",\"state\":\"Returned To Client\",\"comment\":\"Item Return To Seller Delivered at BLR_NandiniLayout\"}],\"status\":\"Returned To Client\",\"scheduled_date\":1713139200000.0,\"date_created\":1713164126000.0,\"pickup_type\":\"regular\",\"slot_start_time\":null,\"slot_end_time\":null}]}";
String s = "{\"message\":\"Success\",\"data\":[{\"id\":2874702,\"client_name\":\"JioMar_1P_RFC\",\"client_order_id\":\"SFXReturn_04\",\"awb_number\":\"R107620433JIF\",\"product_value\":800.0,\"cod_amount\":0,\"payment_mode\":null,\"order_date\":\"2024-04-22\",\"promised_delivery_date\":null,\"status_display\":\"Returned to Seller\",\"status\":\"rts_d\",\"pickup_details\":{\"name\":\"\",\"contact\":\"\",\"address_line_1\":\"\",\"address_line_2\":\"\",\"city\":\"Bengaluru\",\"state\":null,\"pincode\":560007,\"alternate_contact\":\"\",\"sms_contact\":\"\",\"latitude\":null,\"longitude\":null},\"delivery_details\":{\"city\":\"\",\"state\":\"\",\"pincode\":null},\"product_details\":[{\"sku_id\":\"491661272\",\"sku_name\":\"\",\"price\":\"\",\"return_reason\":\"Damaged Product\",\"category\":\"\",\"brand\":\"Brand of the product\",\"additional_details\":\"{\\\"requires_extra_care\\\": false, \\\"type_extra_care\\\": null, \\\"size\\\": 0, \\\"quantity\\\": 5}\",\"seller_name\":\"A02C\",\"seller_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"seller_state\":\"KAR\",\"hsn_code\":null,\"invoice_no\":null,\"sgst_amount\":0.0,\"cgst_amount\":0.0,\"igst_amount\":0.0,\"gstin_number\":null,\"total_tax_value\":0.0,\"qc_required\":false,\"qc_rules\":\"\"},{\"sku_id\":\"491661273\",\"sku_name\":\"\",\"price\":\"\",\"return_reason\":\"Damaged Product\",\"category\":\"\",\"brand\":\"Brand of the product\",\"additional_details\":\"{\\\"requires_extra_care\\\": false, \\\"type_extra_care\\\": null, \\\"size\\\": 0, \\\"quantity\\\": 3}\",\"seller_name\":\"A02C\",\"seller_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"seller_state\":\"KAR\",\"hsn_code\":null,\"invoice_no\":null,\"sgst_amount\":0.0,\"cgst_amount\":0.0,\"igst_amount\":0.0,\"gstin_number\":null,\"total_tax_value\":0.0,\"qc_required\":false,\"qc_rules\":\"\"}],\"tracking_details\":[{\"created\":\"2024-04-22T10:51:36Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"new\",\"status\":\"New\",\"remarks\":\"Item New at BLR_Domlur\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:23:22Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"assigned_for_seller_pickup\",\"status\":\"Assigned For Seller Pickup\",\"remarks\":\"Item assigned for seller pickup atBLR_Domlur\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:23:37Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"ofp\",\"status\":\"Out For Pickup\",\"remarks\":\"Item Out For Pickup at BLR_Domlur\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:26:41Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"rts\",\"status\":\"Return to Seller initiated\",\"remarks\":\"Item Return To Seller at BLR_Domlur\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:26:41Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"recd_at_rev_hub\",\"status\":\"Received at Reverse Hub\",\"remarks\":\"Received at hub Successfully\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:28:37Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"item_manifested\",\"status\":\"Item added to Bag\",\"remarks\":\"Item added to bag at BLR_Domlur\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:29:25Z\",\"location\":\"BLR_Domlur\",\"status_id\":\"in_transit_return\",\"status\":\"In Transit for Return\",\"remarks\":\"Bag in transit for return at BLR_Domlur\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:31:03Z\",\"location\":\"Bangalore DC\",\"status_id\":\"recd_at_dc_rts\",\"status\":\"Recived at RTS DC\",\"remarks\":\"Item Received at RTS DC at Bangalore DC\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:38:30Z\",\"location\":\"BLR_Bannerghatta\",\"status_id\":\"received_at_rts_hub\",\"status\":\"Received at RTS destination hub\",\"remarks\":\"Item received at RTS destination hub BLR_Bannerghatta\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:40:23Z\",\"location\":\"BLR_Bannerghatta\",\"status_id\":\"rts_ofd\",\"status\":\"Out for Delivery for RTS\",\"remarks\":\"Item out for delivery to seller at BLR_Bannerghatta\",\"awb_number\":\"R107620433JIF\"},{\"created\":\"2024-04-22T12:40:57Z\",\"location\":\"BLR_Bannerghatta\",\"status_id\":\"rts_d\",\"status\":\"Returned To Client\",\"remarks\":\"Item Return To Seller Delivered at BLR_Bannerghatta\",\"awb_number\":\"R107620433JIF\"}]}]}";
  String t = "{\"client_order_number\":\"NIT88717311W\",\"request_type\":\"pickup\",\"client_request_id\":\"SF100000192431JIF\",\"client_id\":2772,\"destination_pincode\":null,\"price\":0.0,\"address\":{\"name\":\"Tanmay\",\"phone_number\":\"9304965123\",\"address_line\":\"845, Post Offices, RAICHUR, KARNATAKA PINCode, Search, Post Office Details, All India Post\",\"city\":\"RAICHUR\",\"state\":null,\"pincode\":560077,\"created_at\":\"2024-04-10T10:17:20.000000+0000\",\"updated_at\":\"2024-04-10T10:17:20.000000+0000\",\"alternate_contact\":null},\"skus\":[{\"client_sku_id\":\"491661272\",\"name\":\"Macbook charger\",\"price\":1000.0,\"return_reason\":\"Damaged Product\",\"brand\":\"Brand of the product\",\"category\":\"Laptop\",\"additional_details\":{\"requires_extra_care\":false,\"type_extra_care\":null,\"size\":0,\"quantity\":2},\"seller_details\":{\"regd_name\":\"A02C\",\"regd_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"state\":\"KAR\",\"gstin\":null},\"taxes\":{\"cgst_amount\":0.0,\"sgst_amount\":0.0,\"igst_amount\":0.0,\"total_tax_amount\":0.0},\"hsn_code\":null,\"invoice_no\":null},{\"client_sku_id\":\"491661273\",\"name\":\"Macbook charger1\",\"price\":1000.0,\"return_reason\":\"Damaged Product\",\"brand\":\"Brand of the product\",\"category\":\"Laptop1\",\"additional_details\":{\"requires_extra_care\":false,\"type_extra_care\":null,\"size\":0,\"quantity\":2},\"seller_details\":{\"regd_name\":\"A02C\",\"regd_address\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"state\":\"KAR\",\"gstin\":null},\"taxes\":{\"cgst_amount\":0.0,\"sgst_amount\":0.0,\"igst_amount\":0.0,\"total_tax_amount\":0.0},\"hsn_code\":null,\"invoice_no\":null}],\"seller\":{\"name\":\"S402\",\"phone_number\":\"9999999999\",\"address_line\":\"Survey No. 54/1, Nandihalli village 55th Km stone NH-4 Tumkur road, Oordigree Hobli Taluka\",\"city\":\"Tumkur\",\"state\":null,\"pincode\":562123,\"created_at\":\"2024-04-10T10:17:20.000000+0000\",\"updated_at\":\"2024-04-10T10:17:20.000000+0000\",\"alternate_contact\":null},\"status_last_updated_at\":\"2024-04-10T10:17:20.319272Z\",\"pickup_request_state_histories\":null,\"status\":\"New\",\"scheduled_date\":1712707200000.0,\"date_created\":1712744240000.0,\"pickup_type\":\"regular\",\"slot_start_time\":null,\"slot_end_time\":null,\"total_amount\":0.0,\"address_type\":null,\"invoice_date\":null,\"eway_bill_number\":null,\"awb_number\":\"SF100000192431JIF\",\"message\":\"Success\"}";
  String q = "SELECT s,awm FROM ShipmentMaster s INNER JOIN AWBStatus aws on s.awbNumber=aws.awbNum INNER JOIN AWBStatusMapping awm " +
          "on awm.statusId=aws.statusId WHERE ((cast(:from as timestamp) is null and cast(:to as timestamp) is null) or (s.createdOn between :from and :to)) " +
          "and (:isRtoed is null or aws.isRtoed=:isRtoed) and (:isRvp is null or s.isReversePickup=:isRvp) and (:isExchange is null or s.isExchange=:isExchange) and (s.tenantId =:tenantId)";
  System.out.println(s);
        System.out.println(q);
    }

    private static void testStringTokenize(String s)
    {
        List<String> list = Arrays.asList(s.split(","));

        System.out.println(list);
    }


    private static void testSubstringBetween()
    {
        String s = "fq={!tag=fk4?fk5}availability_string:Exclude\\+out\\+of\\+Stock{!tag=fk2}";

        String tag = StringUtils.substringBetween(s, "{!tag=", "}");
        tag = tag.replaceAll("(\\w+).*", "$1");
        System.out.println(tag);

        s = "AOC APC";
        if (s.endsWith("AOC"))
        {
            s = s.substring(0, s.length()-4);
            s = s + "(AOC)";
        }
        if (s.endsWith("APC"))
        {
            s = s.substring(0, s.length()-4);
            s = s + "%28APC%29";
        }
        System.out.println(s);
    }

    private static void test1()
    {
        String solrQueryStr = "_query_:\"\\{\\!multiMaxScore\\ tie=0.0\\}\\(\\(keywords_text_en\\:apple\\^20.0\\)\\ OR\\ \\(name_text_en\\:apple\\^50.0\\)\\ OR\\ \\(brandName_text_en_mv\\:apple\\^80.0\\)\\ OR\\ \\(code_string\\:apple\\^90.0\\)\\ OR\\ \\(categoryName_text_en_mv\\:apple\\^60.0\\)\\)@#%\\{\\!multiMaxScore\\ tie=0.0\\}\\(\\(keywords_text_en\\:apple\\^20.0\\)\\ OR\\ \\(name_text_en\\:apple\\^50.0\\)\\ OR\\ \\(brandName_text_en_mv\\:apple\\^80.0\\)\\ OR\\ \\(code_string\\:apple\\^90.0\\)\\ OR\\ \\(categoryName_text_en_mv\\:apple\\^60.0\\)\\)\\ OR\\ \\(\\(name_text_en\\:\\\"apple\\\"\\~2.0\\^100.0\\)\\)@#%\\{\\!multiMaxScore\\ tie=0.0\\}\\(\\(keywords_text_en\\:apple\\~\\^10.0\\)\\ OR\\ \\(brandName_text_en_mv\\:apple\\~\\^5.0\\)\\ OR\\ \\(categoryName_text_en_mv\\:apple\\~\\^5.0\\)\\)\" AND ({!func v=\"sum(map(query({!v='code_string\\:491297353'}),0,0,0,1000000.0),map(query({!v='code_string\\:491297354'}),0,0,0,995000.0),map(query({!v='code_string\\:491297355'}),0,0,0,990000.0))\"})";
        String subQuery = "";
        boolean appendQuery = false;
        if (solrQueryStr.startsWith("_query_:\""))
        {
            solrQueryStr = solrQueryStr.substring(9, solrQueryStr.length());
            int queryEnd = solrQueryStr.indexOf("\"");

            if (solrQueryStr.length() > queryEnd+1)
            {
                subQuery = solrQueryStr.substring(queryEnd+1);
            }
            solrQueryStr = solrQueryStr.substring(0, queryEnd);

            appendQuery = true;
        }
        String splitRegex = "@#%";
        String[] allQueries = solrQueryStr.split(splitRegex);
        for (int queryIterator = 0; queryIterator < allQueries.length; queryIterator++)
        {
            String query = allQueries[queryIterator];
            if (appendQuery)
            {
                query = "_query_:\"" + query + "\"" + subQuery;
                System.out.println(query);
            }
        }
    }

    private static void testSplit()
    {
        String shiftType = "    heavy1  |   nonheavy   ";

        {
            shiftType = shiftType.toLowerCase();
            String[] s = shiftType.split("[|]");
            boolean heavy = false, nonheavy = false;
            for (String t : s)
            {
                heavy = heavy || "heavy".equals(t.trim());
                nonheavy = nonheavy || "nonheavy".equals(t.trim());;
            }
            System.out.println(heavy + " " + nonheavy);
        }
    }

    private static void getParams()
    {
        Map<String, String> params = new HashMap<>();
        params.put("1", "abc");
        params.put("2", "def");
        //params.put("3", "ghi");

        StringBuilder queryParams = new StringBuilder();
        String[] list = new String[]{"3", "1", "2"};
        for (String s : list)
        {
            if (params.get(s) != null)
            {
                if (queryParams.length() > 0)
                {
                    queryParams.append("&");
                }
                queryParams.append(s).append("=").append(params.get(s));
            }
        }
        System.out.println(queryParams.toString());
    }

}
