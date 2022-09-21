package com.ayush.khalti.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhaltiRequest {
    private String public_key;
    private String mobile;
    private String transaction_pin;
    private Long amount;
    private String product_identity;
    private String product_name;
    private String product_url;

    private String paymentReference;
}
