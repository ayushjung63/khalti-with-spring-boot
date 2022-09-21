package com.ayush.khalti.pojo;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyKhaltiRequest {
    private String token;
    private Long amount;
}
