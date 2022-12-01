package com.example.INIT_JAVA.DTOs.request;


import com.example.INIT_JAVA.DTOs.response.RoleResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {

    private String name;


    private String password;
    private boolean enabled;
    private boolean tokenExpired;
    private RoleResponseDto role;
}
