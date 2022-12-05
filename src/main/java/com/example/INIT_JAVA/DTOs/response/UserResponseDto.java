package com.example.INIT_JAVA.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private boolean enabled;
    private boolean tokenExpired;
    private RoleResponseDto role;
}
