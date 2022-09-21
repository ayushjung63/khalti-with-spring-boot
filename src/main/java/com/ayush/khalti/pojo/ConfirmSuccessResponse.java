package com.ayush.khalti.pojo;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmSuccessResponse {
    private String token;
    private Long amount;
    private String mobile;
    private String product_identity;
    private String product_name;
}
