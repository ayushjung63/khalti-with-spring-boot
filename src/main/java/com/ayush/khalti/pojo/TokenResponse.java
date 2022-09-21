package com.ayush.khalti.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {
    private String token;
    private boolean pin_created;
    private String pin_created_message;

    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token + '\'' +
                ", pin_created=" + pin_created +
                ", pin_created_message='" + pin_created_message + '\'' +
                '}';
    }
}

