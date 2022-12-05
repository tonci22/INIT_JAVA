package com.example.INIT_JAVA.DTOs.request;

import lombok.Value;

@Value
public class UserLoginRequestDto {

    String username;
    String password;
}
