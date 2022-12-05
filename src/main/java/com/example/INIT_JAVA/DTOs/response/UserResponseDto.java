package com.example.INIT_JAVA.DTOs.response;

import lombok.Value;

@Value
public class UserResponseDto {

    Long id;
    String name;
    boolean enabled;
    boolean tokenExpired;
    RoleResponseDto role;
}
