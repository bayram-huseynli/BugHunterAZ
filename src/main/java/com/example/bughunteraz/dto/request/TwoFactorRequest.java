package com.example.bughunteraz.dto.request;

import lombok.Data;

@Data
public class TwoFactorRequest {

    private String email;

    private int code;
}
