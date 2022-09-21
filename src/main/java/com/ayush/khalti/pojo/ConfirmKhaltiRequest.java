package com.ayush.khalti.pojo;

//{
//        "public_key": "live_public_key_546eb6da05544d7d88961db04fdb9721",
//        "token": "VGMyaKVDQQyorBiQ3W99WL",
//        "confirmation_code": "206964",
//        "transaction_pin": "1234"
//        }

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmKhaltiRequest {
    private String public_key;
    private String token;
    private String confirmation_code;
    private String transaction_pin;
}
